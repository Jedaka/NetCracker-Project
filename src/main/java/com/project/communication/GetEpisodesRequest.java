package com.project.communication;

/**
 *  Created by jedaka on 23.11.2015.
 *
 *  Request that sendEpisode client to server to get episodes
 *
 */
public class GetEpisodesRequest {

    /**
     * Number of episodes to get
     */
    private int numberOfEpisodes;

    /**
     * From what episode client wants to get define number of episodes
     *
     * @see #numberOfEpisodes
     */
    private int fromEpisode;

    /**
     * if true get subscribed user episodes
     * else get episodes from general pool
     */
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

    public void setIsSubscribed(boolean subscribed) {
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
