package com.project.database.dao;

import com.project.model.Subscription;
import com.project.model.Token;
import com.project.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by vganshin on 23.11.15.
 */
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

    public Subscription findByUserAndToken(User user, Token token) {
        Session session = sessionFactory.getCurrentSession();
        return (Subscription) session.createCriteria(Subscription.class).add(Restrictions.eq("user_id", user.getId())).add(Restrictions.eq("token_id", token.getId())).uniqueResult();
    }
}
