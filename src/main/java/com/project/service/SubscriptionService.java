package com.project.service;

import com.project.database.dao.SubscriptionDAO;
import com.project.model.Subscription;
import com.project.model.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by jedaka on 10.12.2015.
 */
@Transactional
@Service
public class SubscriptionService {

    @Autowired
    SubscriptionDAO subscriptionDAO;

    @Transactional(readOnly = true)
    public List<Subscription> findSubscriptionsByToken(Token token){
        return subscriptionDAO.findSubscriptionsByToken(token);
    }

    public void setSubscriptionDAO(SubscriptionDAO subscriptionDAO) {
        this.subscriptionDAO = subscriptionDAO;
    }
}
