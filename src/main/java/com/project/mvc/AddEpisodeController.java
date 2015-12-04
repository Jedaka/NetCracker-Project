package com.project.mvc;

import com.project.communication.AddEpisodeRequest;
import com.project.communication.AddEpisodesRequest;
import com.project.communication.JsonResponse;
import com.project.model.Episode;
import com.project.model.Token;
import com.project.model.User;
import com.project.service.EpisodeService;
import com.project.service.TokenService;
import com.project.service.UserService;
import com.project.util.Mail;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
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
    private UserService userService;
    private Logger logger = Logger.getLogger(AddEpisodeController.class);

    @RequestMapping(value = "/episode")
    public JsonResponse addEpisode(@RequestBody AddEpisodesRequest request){
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("Adding new episodes. ");

        JsonResponse response = new JsonResponse();
        Set<AddEpisodeRequest> episodes = request.getAddEpisodeRequests();

        stringBuilder.append(episodes.size() + " received. ");

        Iterator<AddEpisodeRequest> iterator = episodes.iterator();
        int persistedEpisodeCounter = 0;
        while (iterator.hasNext()) {
            AddEpisodeRequest addEpisodeRequest = iterator.next();
            String recievedToken = addEpisodeRequest.getToken();

            Token token = tokenService.findByToken(recievedToken);
            if (token == null) {
                continue;
            }
            Episode episode = addEpisodeRequest.getEpisode();
            episode.setToken(token);
            try {
                episodeService.save(episode);
                persistedEpisodeCounter++;
            } catch (Exception e) {
                logger.warn(e.getMessage().toString());
                continue;
            }
            List<User> usersForNotification = getUsersWhereSubsIsEqualToken(token);
            try {
                String title = tokenService.getSerialByToken(token).getTitle();
                sentMails(usersForNotification, episode, title);
            }catch (Exception e){
                logger.warn(e.getMessage().toString());
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
    private void sentMails(List<User> users, Episode episode, String title){
        for (int i = 0; i< users.size(); i++){
            Mail mail = new Mail(users.get(i).getEmail());
            mail.send(episode, title);
        }
    }
    public void setTokenService(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    public void setEpisodeService(EpisodeService episodeService) {
        this.episodeService = episodeService;
    }
}
