package com.project.mvc.websocket;

import com.project.communication.AddEpisodeRequest;
import com.project.communication.AddEpisodesRequest;
import com.project.communication.GetEpisodeRequest;
import com.project.communication.JsonResponse;
import com.project.model.Episode;
import com.project.model.Subscription;
import com.project.model.Token;
import com.project.model.User;
import com.project.mvc.GetEpisodeController;
import com.project.service.UserService;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.*;

/**
 * Created by Юрий on 01.12.2015.
 */
@Controller
public class WebSocketController {

    private UserService userService;

    @MessageMapping("/add/episode")
    @SendTo("/get/episodes")
    public JsonResponse addNewEpisode(@RequestBody AddEpisodesRequest request){
        JsonResponse jsonResponse = new JsonResponse();
        GetEpisodeRequest getEpisodeRequest = new GetEpisodeRequest();
        User user = userService.getCurrentUser();
        if (user == null){
            jsonResponse.setMessage("Not authorized user");
            jsonResponse.setStatus(JsonResponse.Status.OK);
            return jsonResponse;
        }
        Iterator<AddEpisodeRequest> iterator = request.getAddEpisodeRequests().iterator();
        Set<AddEpisodeRequest> addEpisodeRequestsForUser = new TreeSet<>();
        while (iterator.hasNext()){
            AddEpisodeRequest addEpisodeRequest = iterator.next();
            addEpisodeRequestsForUser = getEpisodeForUser(addEpisodeRequest, user.getSubscriptions());
        }
        getEpisodeRequest.setFromEpisode(0);
        getEpisodeRequest.setNumberOfEpisodes(addEpisodeRequestsForUser.size() - 1);
        getEpisodeRequest.setIsSubscribed(true);
        return new GetEpisodeController().getEpisodes(getEpisodeRequest);
    }
    private Set<AddEpisodeRequest> getEpisodeForUser(AddEpisodeRequest addEpisodeRequest, Collection<Subscription> subscriptions){
        Set<AddEpisodeRequest> answer = new TreeSet<AddEpisodeRequest>();
        Iterator<Subscription> iterator = subscriptions.iterator();
        while (iterator.hasNext()){
            Token token = iterator.next().getToken();
            if (addEpisodeRequest.getToken().equals(token)){
                answer.add(addEpisodeRequest);
            }
        }
        return answer;
    }
}