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
}
