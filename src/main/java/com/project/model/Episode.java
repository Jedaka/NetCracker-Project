package com.project.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by jedaka on 03.11.2015.
 */
@Table(
        name="EPISODE",
        uniqueConstraints=
        @UniqueConstraint(columnNames={"EPISODE_NUMBER", "SEASON_NUMBER", "TOKEN_ID"})
)
@Entity(name = "EPISODE")
public class Episode {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EPISODE_SEQ")
    @SequenceGenerator(name="EPISODE_SEQ", sequenceName="EPISODE_SEQ",allocationSize=1)
    @JsonIgnore
    private int id;

    @ManyToOne
    @JsonIgnore
    private Token token;
    @Column(name = "SEASON_NUMBER")
    private int seasonNumber;
    @Column(name = "EPISODE_NUMBER")
    private int episodeNumber;
    private String link;
    @Type(type = "timestamp")
    @Column(name = "PUB_DATETIME")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MMM-yyyy, HH:mm:ss", timezone="CET")
    private Date date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Serial getSerial(){
        return token.getSerial();
    }

    public Studio getStudio(){
        return token.getStudio();
    }

    public Token getToken() {
        return token;
    }

    public void setToken(Token token) {
        this.token = token;
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
    public String toString() {
        return "Episode{" +
                "id=" + id +
                ", token=" + token +
                ", seasonNumber=" + seasonNumber +
                ", episodeNumber=" + episodeNumber +
                ", link='" + link + '\'' +
                ", date=" + date +
                '}';
    }
}
