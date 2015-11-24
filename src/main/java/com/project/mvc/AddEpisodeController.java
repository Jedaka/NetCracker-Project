package com.project.mvc;

import com.project.communication.addEpisodes.AddEpisodeRequest;
import com.project.communication.JsonResponse;
import com.project.communication.addEpisodes.EpisodeRequest;
import com.project.model.Episode;
import com.project.model.Token;
import com.project.service.EpisodeService;
import com.project.service.TokenService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

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
    private Logger logger = Logger.getLogger(AddEpisodeController.class);

    @RequestMapping(value = "/episode")
    public JsonResponse addEpisode(@RequestBody AddEpisodeRequest request){
        JsonResponse response = new JsonResponse();
        Set<EpisodeRequest> episodes = request.getEpisodeRequests();
        Iterator<EpisodeRequest> iterator = episodes.iterator();
        while (iterator.hasNext()) {
            String recievedToken = iterator.next().getToken();
            Token token = tokenService.findByToken(recievedToken);

            if (token == null) {
                continue;
            }

            Episode episode = iterator.next().getEpisode();
            episode.setToken(token);
            try {
                episodeService.save(episode);
            } catch (Exception e) {
                continue;
            }

        }
        logger.info("Episodes has been added: " + iterator.toString());
        response.setStatus(JsonResponse.Status.OK);
        response.setMessage(iterator);
        return response;
    }

    public void setTokenService(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    public void setEpisodeService(EpisodeService episodeService) {
        this.episodeService = episodeService;
    }
}
