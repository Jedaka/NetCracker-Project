package com.project.mvc;

import com.project.communication.AddEpisodeRequest;
import com.project.communication.JsonResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by jedaka on 17.11.2015.
 */
@RestController
@RequestMapping(value = "/add")
public class AddEpisodeController {

    @RequestMapping(value = "/episode")
    public JsonResponse addEpisode(AddEpisodeRequest request){
        return new JsonResponse();
    }

}
