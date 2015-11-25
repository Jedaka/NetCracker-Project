package com.project.mvc;

import com.project.communication.GetEpisodeRequest;
import com.project.communication.JsonResponse;
import com.project.model.Episode;
import com.project.model.Subscription;
import com.project.model.Token;
import com.project.model.User;
import com.project.service.EpisodeService;
import com.project.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * Created by jedaka on 23.11.2015.
 */
@RestController
@RequestMapping(value = "/get")
public class GetEpisodeController {

    @Autowired
    EpisodeService episodeService;
    private User user;
    @Autowired
    UserService userService;

    private Logger logger = Logger.getLogger(GetEpisodeController.class);

    @RequestMapping(value = "/episodes")
    public
    @ResponseBody
    JsonResponse getEpisodes(@RequestBody GetEpisodeRequest request) {
        JsonResponse jsonResponse = new JsonResponse(); //create response
        jsonResponse.setStatus(JsonResponse.Status.OK); //answer is ok
        List<Episode> episodeList = null;
        user = userService.getCurrentUser();       // define the user
        if (!request.isSubscribed()) {
            try {
                episodeList = episodeService.getAllOrderByDate();
            } catch (Exception e) {
                logger.error(e);
                jsonResponse.setStatus(JsonResponse.Status.ERROR);
            }
            jsonResponse.setMessage(episodeList);// if user is not authorized then return all episodes
        } else if (user == null && request.isSubscribed()) {
            jsonResponse.setStatus(JsonResponse.Status.ERROR); // fairy-tale situation
        } else if (user != null && request.isSubscribed()) {
            try {
                episodeList = episodesFromIndex(episodeService.getAllOrderByDate(), request.getFromEpisode(), request.getNumberOfEpisodes(), true);

            } catch (Exception e) {
                logger.error(e);
                jsonResponse.setStatus(JsonResponse.Status.ERROR);
            }
        }

        jsonResponse.setMessage(episodeList);
        return jsonResponse;
    }

    private List<Episode> episodesFromIndex(List<Episode> episodes, int start, int count, boolean subscribed) {
        List<Episode> answer = new ArrayList<Episode>();
        HashSet<Token> tokens = getUserTokens(user.getSubscriptions());
        boolean b = false;
        for (Episode episode : episodes) {
            if (episode.getId() == start) {
                b = true;
            }
            if (b) {
                if (subscribed) {
                    Iterator<Token> tokenIterator = tokens.iterator();
                    boolean wasAdded = false;
                    while (tokenIterator.hasNext()) {
                        Token userToken = tokenIterator.next();
                        if (userToken.equals(episode.getToken())) {
                            answer.add(episode);
                            wasAdded = true;
                            break;
                        }
                    }
                    if (wasAdded) {
                        count--;
                        if (count == 0) {
                            break;
                        }
                    }
                } else {
                    answer.add(episode);
                    count--;
                    if (count == 0) {
                        break;
                    }
                }
            }
        }

        return answer;
    }

    private HashSet<Token> getUserTokens(Collection<Subscription> subscriptions) {
        HashSet<Token> tokens = new HashSet<Token>();
        Iterator<Subscription> iterator = subscriptions.iterator();
        while (iterator.hasNext()) {
            Token tmp = iterator.next().getToken();
            tokens.add(tmp);
        }
        return tokens;
    }
}
