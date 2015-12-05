package com.project.database.dao;

import com.project.model.Token;
import com.project.model.User;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.ResultTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by jedaka on 03.11.2015.
 */
@Repository
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
        List<User> users;
        Session session = sessionFactory.getCurrentSession();
        SQLQuery sqlQuery =  session.createSQLQuery("select * from users u where u.id in " +
                "(select subs.users_id from subscription subs where subs.token_id = "+ token.getId() +")");
        sqlQuery.setResultTransformer(new UserResultTransformer());
        users = sqlQuery.list();
        return users;
    }
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;;
    }
    private class UserResultTransformer implements ResultTransformer {
        @Override
        public Object transformTuple(Object[] objects, String[] strings) {
            User user = new User();
            for (int i = 0; i < strings.length; i++) {
                switch (strings[i]){
                    case "ID":{
                        user.setId(((BigDecimal) objects[i]).intValue());
                        break;
                    }
                    case "EMAIL":{
                        user.setEmail((String) objects[i]);
                        break;
                    }
                    case "PASSWORD":{
                        user.setPassword((String) objects[i]);
                        break;
                    }

                }
            }
            return user;
        }

        @Override
        public List transformList(List list) {
            return list;
        }
    }

}
