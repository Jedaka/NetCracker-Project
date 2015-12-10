package com.project.database.dao;

import com.project.model.Subscription;
import com.project.model.Token;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by vganshin on 23.11.15.
 *
 */
@Repository
public class SubscriptionDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public void create(Subscription object) {
        Session session = sessionFactory.getCurrentSession();
        session.save(object);
    }

    public Subscription read(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Subscription.class, id);
    }

    public void delete(Subscription object) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(object);
    }

    public Subscription findByRemoval(String removal){
        Session session = sessionFactory.getCurrentSession();
        return (Subscription) session.createCriteria(Subscription.class).add(Restrictions.eq("removal", removal)).uniqueResult();
    }

    /**
     * Return list of users who follow certain token
     *
     * @param token
     * @return
     */
    public List<Subscription> findSubscriptionsByToken(Token token){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from SUBSCRIPTION s where s.token.id = " + token.getId());
        List<Subscription> users = query.list();
        return users;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
