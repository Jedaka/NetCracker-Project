package com.project.mvc;

import com.project.communication.AddEpisodesRequest;
import com.project.communication.EpisodeInformation;
import com.project.communication.JsonResponse;
import com.project.model.Episode;
import com.project.model.Subscription;
import com.project.model.Token;
import com.project.notification.MailSender;
import com.project.service.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Created by jedaka on 17.11.2015.
 */
@RestController
@RequestMapping(value = "/add")
public class AddEpisodeController {

    @Autowired
    private TokenService tokenService;
    @Autowired
    private EpisodeService episodeService;
    @Autowired
    private WebSocketMessageService webSocketMessageService;
    @Autowired
    private UserService userService;
    @Autowired
    private SubscriptionService subscriptionService;
    @Autowired
    private MailSender mailSender;

    private Logger logger = Logger.getLogger(AddEpisodeController.class);

    /**
     *
     * Controller that handle addEpisodesRequest and add episodes in the data base
     * Additionally, it notify users by email and sendEpisode new episodes to websockets
     *
     * @param request request contains episodes to add
     * @return
     */
    @RequestMapping(value = "/episode")
    public JsonResponse addEpisode(@RequestBody AddEpisodesRequest request){
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("Adding new episodes. ");

        JsonResponse response = new JsonResponse();
        Set<EpisodeInformation> episodes = request.getEpisodesInformation();

        stringBuilder.append(episodes.size() + " received. ");

        Iterator<EpisodeInformation> iterator = episodes.iterator();
        int persistedEpisodeCounter = 0;
        while (iterator.hasNext()) {
            EpisodeInformation episodeInformation = iterator.next();
            String receivedToken = episodeInformation.getToken();

            Token token = tokenService.findByToken(receivedToken);
            if (token == null) {
                continue;
            }

            Episode episode = episodeInformation.getEpisode();
            episode.setToken(token);

            try {
                episodeService.save(episode);
                persistedEpisodeCounter++;
            } catch (Exception e) {
                logger.warn("Exception while persisting episode has occurred " + e.getMessage());
                continue;
            }

            try {
                webSocketMessageService.sendEpisodeToConnectedUsers(episode);
            } catch (Exception e) {
                logger.warn("Exception while sending to websockets has occurred: " + e.getMessage());
            }

            try {
                List<Subscription> usersForNotification = subscriptionService.findSubscriptionsByToken(token);
                sendMails(usersForNotification, episode);
            }catch (Exception e){
                logger.warn("Exception while sending to emails has occurred: " + e.getMessage().toString());
                continue;
            }

        }

        stringBuilder.append(persistedEpisodeCounter + " persisted.");

        response.setStatus(JsonResponse.Status.OK);
        response.setMessage(stringBuilder.toString());
        return response;
    }

    private void sendMails(List<Subscription> subscriptions, Episode episode){

        for (Subscription subscription: subscriptions){
            mailSender.sendEpisode(subscription, episode);
        }

    }

    public void setSubscriptionService(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    public void setTokenService(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    public void setEpisodeService(EpisodeService episodeService) {
        this.episodeService = episodeService;
    }

    public void setWebSocketMessageService(WebSocketMessageService webSocketMessageService) {
        this.webSocketMessageService = webSocketMessageService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public void setMailSender(MailSender mailSender) {
        this.mailSender = mailSender;
    }
}
