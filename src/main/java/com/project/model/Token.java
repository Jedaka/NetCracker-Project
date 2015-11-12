package com.project.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by jedaka on 03.11.2015.
 */
@Entity
public class Token {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @JsonIgnore
    private String token;
    @ManyToOne(cascade = CascadeType.ALL)
    private Serial serial;
    @ManyToOne(cascade = CascadeType.ALL)
    private Studio studio;

    public Token(Serial serial, Studio studio) {
        this.serial = serial;
        this.studio = studio;
    }

    public Token() {

    }

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
