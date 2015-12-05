package com.project.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity(name = "USERS")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USER_PROFILE_SEQ")
    @SequenceGenerator(name="USER_PROFILE_SEQ", sequenceName="USER_PROFILE_SEQ",allocationSize=1)
    @JsonIgnore
    private int id;
    @Column(unique = true)
    private String email;
    @JsonIgnore
    private String password;

    @OneToMany(fetch = FetchType.EAGER, orphanRemoval = true)
    @JoinColumn(name = "USERS_ID", referencedColumnName = "ID", nullable = false)
    @Cascade({CascadeType.ALL})
    private List<Subscription> subscriptions = new ArrayList<Subscription>();

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public User() {

    }

    public List<Subscription> getSubscriptions() {
        return subscriptions;
    }

    public void setSubscriptions(List<Subscription> subscriptions) {
        this.subscriptions = subscriptions;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void addSubscription(Subscription subscription){
        this.subscriptions.add(subscription);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", subscriptions=" + subscriptions +
                '}';
    }
}
