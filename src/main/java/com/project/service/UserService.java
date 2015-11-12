package com.project.service;

import com.project.database.dao.UserDAO;
import com.project.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by jedaka on 10.11.2015.
 */
@Service
@Transactional
public class UserService {

    @Autowired
    private UserDAO userDAO;

    public int save(User user){
        return userDAO.create(user);
    }

    @Transactional(readOnly = true)
    public User read(int id){
        return userDAO.read(id);
    }

    public void update(User user){
        userDAO.update(user);
    }

    public void delete(User user){
        userDAO.delete(user);
    }

    public User findByEmail(String email){
        return userDAO.findByEmail(email);
    }

    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

}
