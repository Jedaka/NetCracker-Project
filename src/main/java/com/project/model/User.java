package com.project.model;


import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;


@Entity(name = "USERS")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USER_PROFILE_SEQ")
    @SequenceGenerator(name="USER_PROFILE_SEQ", sequenceName="USER_PROFILE_SEQ",allocationSize=1)
    private int id;
    private String email;
    private String password;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "USERS_ID", referencedColumnName = "ID", nullable = false)
    @Cascade({CascadeType.SAVE_UPDATE})
    private Collection<Subscription> subscriptions = new ArrayList<Subscription>();

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public User() {
    }

    public Collection<Subscription> getSubscriptions() {
        return subscriptions;
    }

    public void setSubscriptions(Collection<Subscription> subscriptions) {
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
