package com.project.mvc;

import com.project.communication.GetEpisodeRequest;
import com.project.communication.JsonResponse;
import com.project.model.Episode;
import com.project.service.EpisodeService;
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

    @RequestMapping(value = "/episodes")
    public @ResponseBody JsonResponse getEpisodes(@RequestBody GetEpisodeRequest request){
        System.out.println(request.toString());
        JsonResponse jsonResponse = new JsonResponse();
        jsonResponse.setStatus(JsonResponse.Status.OK);
        List<Episode> episodeList = null;
        try {
            episodeList = episodeService.getAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        jsonResponse.setMessage(episodeList);
        return jsonResponse;
    }
}
