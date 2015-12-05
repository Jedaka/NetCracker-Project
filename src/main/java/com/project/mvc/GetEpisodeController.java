package com.project.mvc;

import com.project.communication.GetEpisodesRequest;
import com.project.communication.JsonResponse;
import com.project.model.Episode;
import com.project.model.User;
import com.project.service.EpisodeService;
import com.project.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
    public @ResponseBody JsonResponse getEpisodes(@RequestBody GetEpisodesRequest request) {

        int numberOfEpisodes = request.getNumberOfEpisodes();
        boolean isSubscribed = request.isSubscribed();
        int fromEpisode = request.getFromEpisode();
        JsonResponse jsonResponse = new JsonResponse();
        List<Episode> episodeList;
        user = userService.getCurrentUser();


        if (!isSubscribed) {

            try {
                episodeList = episodeService.get(numberOfEpisodes, fromEpisode);
                jsonResponse.setStatus(JsonResponse.Status.OK);
                jsonResponse.setMessage(episodeList);
            } catch (Exception e) {
                logger.error(e);
                jsonResponse.setStatus(JsonResponse.Status.ERROR);
                jsonResponse.setMessage("Exception was thrown. See server logs");
            }

        } else if (user == null && isSubscribed) {

            jsonResponse.setStatus(JsonResponse.Status.ERROR);
            jsonResponse.setMessage("You don't have permissions for that. Please sign up.");
            logger.info("Unregistered user tried to access subscriptions. Forbidden");

        } else if (user != null && isSubscribed) {

            try {
                episodeList = userService.getSubscribedEpisodes(user, numberOfEpisodes, fromEpisode);
                jsonResponse.setStatus(JsonResponse.Status.OK);
                jsonResponse.setMessage(episodeList);

            } catch (Exception e) {
                e.printStackTrace();
                logger.error(e);
                jsonResponse.setStatus(JsonResponse.Status.ERROR);
                jsonResponse.setMessage("Exception was thrown. See server logs");
            }

        }
        return jsonResponse;
    }

}
