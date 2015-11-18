package com.project.parsing;

/**
 * Created by Максим on 18.11.2015.
 */
public class AddEpisodeRequest {
    private String token;

    private EpisodeTMP episode;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public EpisodeTMP getEpisode() {
        return episode;
    }

    public void setEpisode(EpisodeTMP episode) {
        this.episode = episode;
    }

}
