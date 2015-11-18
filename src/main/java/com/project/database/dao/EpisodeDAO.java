package com.project.database.dao;

import com.project.model.Episode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by jedaka on 17.11.2015.
 */
public class EpisodeDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public int create(Episode object) throws Exception{
        Session session = sessionFactory.getCurrentSession();
        return (Integer) session.save(object);
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
