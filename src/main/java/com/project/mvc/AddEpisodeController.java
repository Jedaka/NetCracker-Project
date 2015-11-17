package com.project.mvc;

import com.project.communication.AddEpisodeRequest;
import com.project.communication.JsonResponse;
import com.project.model.Episode;
import com.project.model.Token;
import com.project.service.EpisodeService;
import com.project.service.TokenService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        String recievedToken = request.getToken();
        Token token = tokenService.findByToken(recievedToken);

        if (token == null){
            response.setStatus(JsonResponse.Status.ERROR);
            response.setMessage("invalid token");
            return response;
        }

        Episode episode = request.getEpisode();
        episode.setToken(token);
        try {
            episodeService.save(episode);
        } catch (Exception e) {
            logger.error(e);
            response.setStatus(JsonResponse.Status.ERROR);
            response.setMessage("exception was thrown");
            return response;
        }
        logger.info("Episode has been added: " + episode);
        response.setStatus(JsonResponse.Status.OK);
        response.setMessage(episode);
        return response;
    }

    public void setTokenService(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    public void setEpisodeService(EpisodeService episodeService) {
        this.episodeService = episodeService;
    }
}
