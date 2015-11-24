package com.project.communication;

import java.util.Set;

/**
 * Created by jedaka on 17.11.2015.
 */
public class AddEpisodesRequest {

    private Set<AddEpisodeRequest> addEpisodeRequest;

    public Set<AddEpisodeRequest> getAddEpisodeRequest() {
        return addEpisodeRequest;
    }

    public void setAddEpisodeRequest(Set<AddEpisodeRequest> addEpisodeRequest) {
        this.addEpisodeRequest = addEpisodeRequest;
    }

    @Override
    public String toString() {
        return "AddEpisodesRequest{" +
                "addEpisodeRequest=" + addEpisodeRequest +
                '}';
    }
}
