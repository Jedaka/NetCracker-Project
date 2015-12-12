package com.project.model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.math.BigInteger;
import java.security.SecureRandom;

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

    @JsonIgnore
    private String removal = new BigInteger(300, new SecureRandom()).toString(36);

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "users_id", insertable = false, updatable = false)
    private User user;

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

    public void setRemoval(String removal) {
        this.removal = removal;
    }

    public String getRemoval() {
        return removal;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    @Override
    public String toString() {
        return "Subscription{" +
                "id=" + id +
                ", token=" + token +
                ", removal='" + removal + '\'' +
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
