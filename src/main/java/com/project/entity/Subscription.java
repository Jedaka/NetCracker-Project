package com.project.entity;

import org.hibernate.annotations.*;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import javax.persistence.Entity;

/**
 * Created by jedaka on 03.11.2015.
 */
@Entity(name = "SUBSCRIPTION")
public class Subscription {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SUBSCRIPTION_SEQ")
    @SequenceGenerator(name = "SUBSCRIPTION_SEQ", sequenceName = "SUBSCRIPTION_SEQ", allocationSize = 1)
    private int id;
    @Cascade({CascadeType.SAVE_UPDATE})
    @OneToOne
    private Serial serial;
    @Cascade({CascadeType.SAVE_UPDATE})
    @OneToOne
    private Studio studio;

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
        return "Subscription{" +
                "id=" + id +
                ", serial=" + serial +
                ", studio=" + studio +
                '}';
    }
}
