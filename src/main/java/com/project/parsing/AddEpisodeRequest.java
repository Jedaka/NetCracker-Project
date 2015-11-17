package com.project.parsing;

import com.project.model.Episode;

/**
 * Created by Максим on 17.11.2015.
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
