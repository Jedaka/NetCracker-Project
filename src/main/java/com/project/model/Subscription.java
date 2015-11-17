package com.project.model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

/**
 * Created by jedaka on 03.11.2015.
 */
@Entity(name = "SUBSCRIPTION")
public class Subscription {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SUBSCRIPTION_SEQ")
    @SequenceGenerator(name = "SUBSCRIPTION_SEQ", sequenceName = "SUBSCRIPTION_SEQ", allocationSize = 1)
    @JsonIgnore
    private int id;

    @OneToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    private Token token;

    public Subscription() {

    }

    //Temporary constructor
    public Subscription(Serial serial, Studio studio){
        Token token = new Token(serial, studio);
        this.token = token;
    }

    public Serial getSerial(){
        return token.getSerial();
    }

    public Studio getStudio(){
        return token.getStudio();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Token getToken() {
        return token;
    }

    public void setToken(Token token) {
        this.token = token;
    }

    @Override

    public String toString() {
        return "Subscription{" +
                "id=" + id +
                ", serial=" + getSerial() +
                ", studio=" + getStudio() +
                '}';
    }
}
