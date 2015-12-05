package com.project.mvc;

import com.project.communication.EpisodeInformation;
import com.project.communication.AddEpisodesRequest;
import com.project.communication.JsonResponse;
import com.project.model.Episode;
import com.project.model.Token;
import com.project.model.User;
import com.project.notification.Mail;
import com.project.service.EpisodeService;
import com.project.service.TokenService;
import com.project.service.UserService;
import com.project.service.WebSocketMessageService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.internet.AddressException;
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
    private WebSocketMessageService messageService;
    @Autowired
    private UserService userService;

    private Logger logger = Logger.getLogger(AddEpisodeController.class);

    /**
     *
     * Controller that handle addEpisodesRequest and add episodes in the data base
     * Additionally, it notify users by email and send new episodes to websockets
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
                messageService.sendEpisodeToConnectedUsers(episode);
            } catch (Exception e) {
                logger.warn("Exception while sending to websockets has occurred: " + e.getMessage());
            }

            try {
                List<User> usersForNotification = getUsersWhereSubsIsEqualToken(token);
                sendMails(usersForNotification, episode, token.getSerial().getTitle());
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

    private List<User> getUsersWhereSubsIsEqualToken(Token token){
        List<User> users = userService.findUsersBySubscription(token);
        return users;
    }

    private void sendMails(List<User> users, Episode episode, String title){
        String[] internetAddresses = new String[users.size()];
        for (int i = 0; i< users.size(); i++){
            internetAddresses[i] = users.get(i).getEmail();
        }
        Mail mail = null;
        try {
            mail = new Mail(internetAddresses);
            logger.info("All emails are correct");
        } catch (AddressException e) {
            e.printStackTrace();
            logger.warn(e.getMessage() + "There is incorrect emails");

        }
        mail.send(episode, title);
    }

    public void setTokenService(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    public void setEpisodeService(EpisodeService episodeService) {
        this.episodeService = episodeService;
    }

    public void setMessageService(WebSocketMessageService messageService) {
        this.messageService = messageService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
