package com.project.database.dao;

import com.project.model.Serial;
import com.project.model.Token;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Юрий on 05.12.2015.
 */
public class SerialDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public Serial getSerialByToken(Token token){
        Session session = sessionFactory.getCurrentSession();
        return ((Token) session.createCriteria(Token.class).add(Restrictions.eq("token", token.getToken())).uniqueResult()).getSerial();
    }
}
