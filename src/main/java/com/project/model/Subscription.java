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
    private int id;

    @ManyToOne()
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Subscription that = (Subscription) o;

        if (id != that.id) return false;
        return token.equals(that.token);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + token.hashCode();
        return result;
    }
}
