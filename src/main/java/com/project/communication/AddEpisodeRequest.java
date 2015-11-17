package com.project.communication;

import com.project.model.Episode;

/**
 * Created by jedaka on 17.11.2015.
 */
public class AddEpisodeRequest {

    private String token;
    private Episode episode;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Episode getEpisode() {
        return episode;
    }

    public void setEpisode(Episode episode) {
        this.episode = episode;
    }
}
