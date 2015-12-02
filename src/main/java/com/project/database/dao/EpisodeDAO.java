package com.project.database.dao;

import com.project.model.Episode;
import com.project.model.Token;
import com.project.model.User;
import com.project.service.TokenService;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.ResultTransformer;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * Created by jedaka on 17.11.2015.
 */
public class EpisodeDAO {

    @Autowired
    private SessionFactory sessionFactory;
    @Autowired
    private TokenService tokenService;

    public int create(Episode object) throws Exception {
        Session session = sessionFactory.getCurrentSession();
        return (Integer) session.save(object);
    }

    public List<Episode> getAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createCriteria(Episode.class).addOrder(Order.desc("date")).list();
    }

    public List<Episode> get(int count, int from) {
        Session session = sessionFactory.getCurrentSession();
        SQLQuery query = session.createSQLQuery("SELECT * FROM " +
                "(SELECT  q.*, rownum r FROM " +
                "(SELECT * FROM EPISODE " +
                " ORDER BY PUB_DATETIME DESC)" +
                " q )" +
                " WHERE r BETWEEN " + from + " AND " + (count + from));

        query.setResultTransformer(new EpisodeResultTransformer());
        List<Episode> list = query.list();
        return list;
    }

    public List<Episode> getAllOrderByDateWhereTokenId(int token) {
        Session session = sessionFactory.getCurrentSession();
        return session.createCriteria(Episode.class).add(Restrictions.eq("token", token)).addOrder(Order.desc("date")).list();
    }

    public List<Episode> getEpisodesForUser(User user, int count, int from) {
        Session session = sessionFactory.getCurrentSession();
        String email = user.getEmail();
        SQLQuery query = session.createSQLQuery("SELECT * FROM " +
                "(SELECT  q.*, rownum r FROM " +
                "(SELECT * FROM EPISODE WHERE TOKEN_ID IN " +
                "(SELECT TOKEN_ID FROM SUBSCRIPTION WHERE USERS_ID = " +
                "(SELECT ID FROM USERS WHERE EMAIL = '" + email + "')" +
                ") ORDER BY PUB_DATETIME DESC)" +
                " q )" +
                " WHERE r BETWEEN " + from + " AND " + (count + from));

        query.setResultTransformer(new EpisodeResultTransformer());
        List<Episode> list = query.list();
        return list;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void setTokenService(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    private class EpisodeResultTransformer implements ResultTransformer {
        @Override
        public Object transformTuple(Object[] objects, String[] strings) {
            Episode episode = new Episode();
            for (int i = 0; i < strings.length; i++) {
                switch (strings[i]) {
                    case "ID":
                        episode.setId(((BigDecimal) objects[i]).intValue());
                        break;
                    case "PUB_DATETIME":
                        episode.setDate(new Date(((Timestamp) objects[i]).getTime()));
                        break;
                    case "EPISODE_NUMBER":
                        episode.setEpisodeNumber(((BigDecimal) objects[i]).intValue());
                        break;
                    case "LINK":
                        episode.setLink((String) objects[i]);
                        break;
                    case "SEASON_NUMBER":
                        episode.setSeasonNumber(((BigDecimal) objects[i]).intValue());
                        break;
                    case "TOKEN_ID":
                        Integer tokenID = ((BigDecimal) objects[i]).intValue();
                        Token token = tokenService.get(tokenID);
                        episode.setToken(token);
                        break;
                }
            }
            return episode;
        }

        @Override
        public List transformList(List list) {
            return list;
        }

    }
}
