package com.project.communication;

/**
 * Created by jedaka on 23.11.2015.
 */
public class GetEpisodeRequest {

    private int numberOfEpisodes;
    private int fromEpisode; // ID!!!
    private boolean isSubscribed;

    public int getNumberOfEpisodes() {
        return numberOfEpisodes;
    }

    public void setNumberOfEpisodes(int numberOfEpisodes) {
        this.numberOfEpisodes = numberOfEpisodes;
    }

    public int getFromEpisode() {
        return fromEpisode;
    }

    public void setFromEpisode(int fromEpisode) {
        this.fromEpisode = fromEpisode;
    }

    public boolean isSubscribed() {
        return isSubscribed;
    }

    public void setSubscribed(boolean subscribed) {
        isSubscribed = subscribed;
    }

    @Override
    public String toString() {
        return "GetEpisodeRequest{" +
                "numberOfEpisodes=" + numberOfEpisodes +
                ", fromEpisode=" + fromEpisode +
                ", isSubscribed=" + isSubscribed +
                '}';
    }
}
