package com.project.communication;

import com.project.model.Episode;

/**
 *  Created by jedaka on 25.11.2015.
 *
 *  Subrequest that contains enough information to add episode to the database
 *
 *  Used in @AddEpisodesRequest
 *
 *
 */
public class EpisodeInformation {

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

    @Override
    public String toString() {
        return "AddEpisodeRequest{" +
                "token='" + token + '\'' +
                ", episode=" + episode +
                '}';
    }
}
