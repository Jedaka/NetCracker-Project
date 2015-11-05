package com.project.entity;

import javax.persistence.*;

/**
 * Created by jedaka on 03.11.2015.
 */

@Entity(name = "SERIAL")
public class Serial {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SERIAL_SEQ")
    @SequenceGenerator(name="SERIAL_SEQ", sequenceName="SERIAL_SEQ",allocationSize=1)
    private int id;
    private String title;
    private String language;

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
