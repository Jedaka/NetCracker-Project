package com.project.model;

import javax.persistence.*;

/**
 * Created by jedaka on 03.11.2015.
 */
@Entity
public class Token {

    @Id
    private String token;
    @OneToOne
    private Serial serial;
    @OneToOne
    private Studio studio;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
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

    @Override
    public String toString() {
        return "Token{" +
                "token=" + token +
                ", serial=" + serial +
                ", studio=" + studio +
                '}';
    }
}
