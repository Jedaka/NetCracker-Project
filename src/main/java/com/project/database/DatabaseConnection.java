package com.project.database;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DatabaseConnection {

    private static SessionFactory sessionFactory =  new Configuration().configure().buildSessionFactory();

    public static SessionFactory getSessionFactory(){
        return sessionFactory;
    }

    private DatabaseConnection(){}

}
