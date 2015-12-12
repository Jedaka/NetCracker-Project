package com.project.communication;

import java.util.Set;

/**
 *  Created by jedaka on 17.11.2015.
 *
 *  Request that parser sendEpisode to the server to add a new episodes to the database.
 *
 *  @see EpisodeInformation
 *
 *
 */
public class AddEpisodesRequest {

    private Set<EpisodeInformation> episodesInformation;

    public Set<EpisodeInformation> getEpisodesInformation() {
        return episodesInformation;
    }

    public void setEpisodeInformations(Set<EpisodeInformation> episodesInformation) {
        this.episodesInformation = episodesInformation;
    }

    @Override
    public String toString() {
        return "AddEpisodesRequest{" +
                "addEpisodeRequests=" + episodesInformation +
                '}';
    }
}
