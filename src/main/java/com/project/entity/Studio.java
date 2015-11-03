package com.project.entity;

import javax.persistence.*;

/**
 * Created by jedaka on 03.11.2015.
 */
@Entity(name = "STUDIO")
public class Studio {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "STUDIO_SEQ")
    @SequenceGenerator(name="STUDIO_SEQ", sequenceName="STUDIO_SEQ",allocationSize=1)
    private int id;

    private String name;
    private String Language;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLanguage() {
        return Language;
    }

    public void setLanguage(String language) {
        Language = language;
    }

    @Override
    public String toString() {
        return "Studio{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", Language='" + Language + '\'' +
                '}';
    }
}
