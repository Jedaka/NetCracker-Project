package com.project.model;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Serial serial = (Serial) o;

        if (id != serial.id) return false;
        if (!title.equals(serial.title)) return false;
        return language.equals(serial.language);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + title.hashCode();
        result = 31 * result + language.hashCode();
        return result;
    }
}
