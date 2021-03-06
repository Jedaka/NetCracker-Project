package com.project.service;

import com.project.communication.ChangePasswordRequest;
import com.project.database.dao.EpisodeDAO;
import com.project.database.dao.SubscriptionDAO;
import com.project.database.dao.UserDAO;
import com.project.model.Episode;
import com.project.model.Subscription;
import com.project.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by jedaka on 10.11.2015.
 */
@Service
@Transactional
public class UserService {

    @Autowired
    private UserDAO userDAO;
    @Autowired
    private EpisodeDAO episodeDAO;
    @Autowired
    private SubscriptionDAO subscriptionDAO;

    @Transactional
    public int save(User user){
        return userDAO.create(user);
    }

    @Transactional(readOnly = true)
    public User read(int id){
        return userDAO.read(id);
    }

    @Transactional
    public void update(User user){
        userDAO.update(user);
    }

    @Transactional
    public void delete(User user){
        userDAO.delete(user);
    }

    @Transactional(readOnly = true)
    public User findByEmail(String email){
        return userDAO.findByEmail(email);
    }

    @Transactional(readOnly = true)
    public List<Episode> getSubscribedEpisodes(User user, int count){
        return this.getSubscribedEpisodes(user, count, 0);
    }

    @Transactional(readOnly = true)
    public List<Episode> getSubscribedEpisodes(User user, int count, int from){
        return episodeDAO.getEpisodesForUser(user, count, from);
    }

    @Transactional
    public void deleteSubscriptionByRemoval(String removal){
        Subscription subscription = subscriptionDAO.findByRemoval(removal);
        User user = subscription.getUser();
        user.getSubscriptions().remove(subscription);
        this.update(user);
    }

    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        return findByEmail(email);
    }

    public Boolean changePassword(ChangePasswordRequest passwordForm) {
        User user = getCurrentUser();
        if (user.getPassword().equals(passwordForm.getOldPassword())) {
            user.setPassword(passwordForm.getNewPassword());
            save(user);
            return true;
        }
        return false;
    }

    public void setEpisodeDAO(EpisodeDAO episodeDAO) {
        this.episodeDAO = episodeDAO;
    }

    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public void setSubscriptionDAO(SubscriptionDAO subscriptionDAO) {
        this.subscriptionDAO = subscriptionDAO;
    }
}
