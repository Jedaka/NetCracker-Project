package com.project.communication.addEpisodes;

import com.project.model.Episode;

import java.util.Set;
import java.util.TreeSet;

/**
 * Created by jedaka on 17.11.2015.
 */
public class AddEpisodeRequest {
    private Set<EpisodeRequest> episodeRequests;

    public Set<EpisodeRequest> getEpisodeRequests() {
        return episodeRequests;
    }

    public void setEpisodeRequests(Set<EpisodeRequest> episodeRequests) {
        this.episodeRequests = episodeRequests;
    }

    @Override
    public String toString() {
        return "AddEpisodeRequest{" +
                "episodeRequests=" + episodeRequests +
                '}';
    }


}
