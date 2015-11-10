package com.project.database.dao;

import com.project.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by jedaka on 03.11.2015.
 */
public class UserDAO implements GenericDAO<User> {

    @Autowired
    public SessionFactory sessionFactory;

    @Override
    public boolean save(User object) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.save(object);
            session.getTransaction().commit();
        } catch (Exception e) {
            //TODO
            //Logger HERE
            session.getTransaction().rollback();
            return false;
        } finally {
            session.close();
        }
        return true;
    }

    @Override
    public User getById(int id) {
        User user = null;
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            user = session.get(User.class, id);
            session.getTransaction().commit();
        } catch (Exception e) {
            //TODO
            //Logger HERE
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return user;
    }

    public User getByEmail(String email){
        User user = null;
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            user = (User) session.createCriteria(User.class).add(Restrictions.eq("email", email)).uniqueResult();
            session.getTransaction().commit();
        } catch (Exception e) {
            //TODO
            e.printStackTrace();
            //Logger HERE
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return user;
    }


    @Override
    public boolean update(User object) {

        return false;
    }

    @Override
    public boolean delete(User object) {
        return false;
    }

    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public User getByPK(String PK) {
        return null;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


}
