package com.project.database.dao;

import com.project.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

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

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

}
