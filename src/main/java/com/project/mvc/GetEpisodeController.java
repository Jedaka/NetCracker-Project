package com.project.mvc;

import com.project.communication.GetEpisodeRequest;
import com.project.communication.JsonResponse;
import com.project.model.Episode;
import com.project.model.Subscription;
import com.project.model.User;
import com.project.service.EpisodeService;
import com.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collection;
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
    @RequestMapping(value = "/episodes")
    public @ResponseBody JsonResponse getEpisodes(@RequestBody GetEpisodeRequest request){
        JsonResponse jsonResponse = new JsonResponse(); //create response
        jsonResponse.setStatus(JsonResponse.Status.OK); //answer is ok
        List<Episode> episodeList = null;
        user =userService.getCurrentUser();       // define the user
        if (user == null && !request.isSubscribed()) {
            try {
                episodeList = episodeService.getAll();
            } catch (Exception e) {
                jsonResponse.setStatus(JsonResponse.Status.ERROR);
            }
            jsonResponse.setMessage(episodeList);// if user is not authorized then return all episodes
        }
        if (user == null && request.isSubscribed()){
            jsonResponse.setStatus(JsonResponse.Status.ERROR); // fairy-tale situation
        }
        if (user != null && !request.isSubscribed()){  // return all episodes to choosing
            try {
                episodeList = episodesFromIndex(episodeService.getAll(),request.getFromEpisode(), request.getNumberOfEpisodes(), false);

            } catch (Exception e) {
                jsonResponse.setStatus(JsonResponse.Status.ERROR);
            }
        }
        if (user != null && request.isSubscribed()){
            try {
                episodeList = episodesFromIndex(episodeService.getAll(), request.getFromEpisode(), request.getNumberOfEpisodes(), true);

            } catch (Exception e) {
                jsonResponse.setStatus(JsonResponse.Status.ERROR);
            }
        }

        jsonResponse.setMessage(episodeList);
        return jsonResponse;
    }
    private List<Episode> episodesFromIndex(List<Episode> episodes, int start, int count, boolean subscribed){
        List<Episode> answer = new ArrayList<Episode>();
        Collection<Subscription> userSubscriptions = user.getSubscriptions();
        for (int i = start; i < start+count; i++){
            if (subscribed) {
                if (userSubscriptions.contains(episodes.get(i))){
                    answer.add(episodes.get(i));
                }else{
                    count++;
                }
            }else{
                answer.add(episodes.get(i));
            }
        }
        return answer;
    }
}
