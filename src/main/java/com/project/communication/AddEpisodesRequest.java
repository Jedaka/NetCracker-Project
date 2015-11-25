package com.project.communication;

import java.util.Set;

/**
 * Created by jedaka on 17.11.2015.
 */
public class AddEpisodesRequest {

    private Set<AddEpisodeRequest> addEpisodeRequests;

    public Set<AddEpisodeRequest> getAddEpisodeRequests() {
        return addEpisodeRequests;
    }

    public void setAddEpisodeRequests(Set<AddEpisodeRequest> addEpisodeRequests) {
        this.addEpisodeRequests = addEpisodeRequests;
    }

    @Override
    public String toString() {
        return "AddEpisodesRequest{" +
                "addEpisodeRequests=" + addEpisodeRequests +
                '}';
    }
}
