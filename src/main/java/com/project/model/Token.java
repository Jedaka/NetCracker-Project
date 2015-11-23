package com.project.model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.UUID;

/**
 * Created by jedaka on 03.11.2015.
 */
@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"serial_id", "studio_id"}))
public class Token {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TOKEN_SEQ")
    @SequenceGenerator(name = "TOKEN_SEQ", sequenceName = "TOKEN_SEQ", allocationSize = 1)
    @JsonIgnore
    private int id;

    @JsonIgnore
    private String token = UUID.randomUUID().toString();

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Token{" +
                "id=" + id +
                ", token='" + token + '\'' +
                ", serial=" + serial +
                ", studio=" + studio +
                '}';
    }
}
