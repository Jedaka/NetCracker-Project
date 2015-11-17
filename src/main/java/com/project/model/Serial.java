package com.project.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

/**
 * Created by jedaka on 03.11.2015.
 */

@Entity(name = "SERIAL")
public class Serial {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SERIAL_SEQ")
    @SequenceGenerator(name="SERIAL_SEQ", sequenceName="SERIAL_SEQ",allocationSize=1)
    @JsonIgnore
    private int id;
    private String title;
    private String language;

    public Serial(String title, String language) {
        this.title = title;
        this.language = language;
    }

    public Serial() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    @Override
    public String toString() {
        return "Serial{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", language='" + language + '\'' +
                '}';
    }
}
