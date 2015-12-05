package com.project.communication;

import com.project.model.Episode;

/**
 * Created by jedaka on 25.11.2015.
 */
public class AddEpisodeRequest {

    private String token;

    private Episode episode;

    private String serialTitle;

    public String getSerialTitle() {
        return serialTitle;
    }

    public void setSerialTitle(String serialTitle) {
        this.serialTitle = serialTitle;
    }

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

    @Override
    public String toString() {
        return "AddEpisodeRequest{" +
                "token='" + token + '\'' +
                ", episode=" + episode +
                ", serialTitle='" + serialTitle + '\'' +
                '}';
    }
}
