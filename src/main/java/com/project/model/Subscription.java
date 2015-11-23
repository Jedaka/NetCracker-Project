package com.project.model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

/**
 * Created by jedaka on 03.11.2015.
 */
@Entity(name = "SUBSCRIPTION")
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"token_id", "users_id"}))
public class Subscription {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SUBSCRIPTION_SEQ")
    @SequenceGenerator(name = "SUBSCRIPTION_SEQ", sequenceName = "SUBSCRIPTION_SEQ", allocationSize = 1)
    @JsonIgnore
    private int id;

    @ManyToOne(cascade = CascadeType.ALL)
    private Token token;

    public Subscription() {

    }

    //Temporary constructor
    public Subscription(Token token) {
        this.token = token;
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
                ", token=" + getToken() +
                '}';
    }
}
