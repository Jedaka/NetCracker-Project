package com.project.entity;


import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;


@Entity(name = "USER_PROFILE")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USER_PROFILE_SEQ")
    @SequenceGenerator(name="USER_PROFILE_SEQ", sequenceName="USER_PROFILE_SEQ",allocationSize=1)
    private int id;
    private String email;
    private String name;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "USER_PROFILE_ID")
    @Cascade({CascadeType.SAVE_UPDATE})
    private Collection<Subscription> subscriptions = new ArrayList<Subscription>();

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", subscriptions=" + subscriptions +
                '}';
    }
}
