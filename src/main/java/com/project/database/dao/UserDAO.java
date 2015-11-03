package com.project.database.dao;

import com.project.database.DatabaseConnection;
import com.project.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

/**
 * Created by jedaka on 03.11.2015.
 */
public class UserDAO implements GenericDAO<User> {

    static SessionFactory sessionFactory;

    public UserDAO() {
        sessionFactory = DatabaseConnection.getSessionFactory();
    }

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


}
