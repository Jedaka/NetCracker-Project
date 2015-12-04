package com.project.database.dao;

import com.project.model.Token;
import com.project.model.User;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jedaka on 03.11.2015.
 */
public class UserDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public int create(User object) {
        Session session = sessionFactory.getCurrentSession();
        return (Integer) session.save(object);
    }

    public User read(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(User.class, id);
    }

    public void update(User object) {
        Session session = sessionFactory.getCurrentSession();
        session.update(object);
    }

    public void delete(User object) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(object);
    }

    public User findByEmail(String email){
        Session session = sessionFactory.getCurrentSession();
        User user = (User) session.createCriteria(User.class).add(Restrictions.eq("email", email)).uniqueResult();
        return user;
    }

    public List<User> findUsersBySubscription(Token token){
        List<User> users = new ArrayList<>();
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("EntityManager");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Query query = entityManager.createQuery("(SELECT user FROM User user WHERE user.id = " +
                "(SELECT subs.user.id FROM Subscription subs where subs.token =" + token + "))");
        users = query.getResultList();
        return users;
    }
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

}
