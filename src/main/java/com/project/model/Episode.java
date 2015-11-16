package com.project.model;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by jedaka on 03.11.2015.
 */
@Entity(name = "EPISODE")
public class Episode {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EPISODE_SEQ")
    @SequenceGenerator(name="EPISODE_SEQ", sequenceName="EPISODE_SEQ",allocationSize=1)
    private int id;
    @OneToOne
    private Serial serial;
    @OneToOne
    private Studio studio;
    @Column(name = "SEASON_NUMBER")
    private int seasonNumber;
    @Column(name = "EPISODE_NUMBER")
    private int episodeNumber;
    private String link;
    @Type(type = "timestamp")
    @Column(name = "PUB_DATETIME")
    private Date date;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Serial getSerial() {
        return serial;
    }

    public void setSerial(Serial serial) {
        this.serial = serial;
    }

    public Studio getStudio() {
        return studio;
    }

    public void setStudio(Studio studio) {
        this.studio = studio;
    }

    public int getSeasonNumber() {
        return seasonNumber;
    }

    public void setSeasonNumber(int seasonNumber) {
        this.seasonNumber = seasonNumber;
    }

    public int getEpisodeNumber() {
        return episodeNumber;
    }

    public void setEpisodeNumber(int episodeNumber) {
        this.episodeNumber = episodeNumber;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Episode episode = (Episode) o;

        if (id != episode.id) return false;
        if (seasonNumber != episode.seasonNumber) return false;
        if (episodeNumber != episode.episodeNumber) return false;
        if (!serial.equals(episode.serial)) return false;
        if (!studio.equals(episode.studio)) return false;
        if (link != null ? !link.equals(episode.link) : episode.link != null) return false;
        return date.equals(episode.date);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + serial.hashCode();
        result = 31 * result + studio.hashCode();
        result = 31 * result + seasonNumber;
        result = 31 * result + episodeNumber;
        result = 31 * result + (link != null ? link.hashCode() : 0);
        result = 31 * result + date.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Episode{" +
                "id=" + id +
                ", serial=" + serial +
                ", studio=" + studio +
                ", seasonNumber=" + seasonNumber +
                ", episodeNumber=" + episodeNumber +
                ", link='" + link + '\'' +
                ", date=" + date +
                '}';
    }
}
