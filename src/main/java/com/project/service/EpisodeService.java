package com.project.service;

import com.project.database.dao.EpisodeDAO;
import com.project.model.Episode;
import com.project.websockets.store.WebSocketSessionStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by jedaka on 17.11.2015.
 */
@Service
public class EpisodeService {

    @Autowired
    private EpisodeDAO episodeDAO;

    @Autowired
    private WebSocketSessionStore sessionStore;

    @Transactional
    public int save(Episode episode) throws Exception{
        return this.episodeDAO.create(episode);
    }

    @Transactional(readOnly = true)
    public List<Episode> getAll() throws Exception{
        return this.episodeDAO.getAll();
    }

    @Transactional(readOnly = true)
    public List<Episode> get(int count, int from) throws Exception{
        return this.episodeDAO.get(count, from);
    }

    @Transactional(readOnly = true)
    public List<Episode> getAllOrderByDateWhereTokenId(int token) throws Exception{
        return this.episodeDAO.getAllOrderByDateWhereTokenId(token);
    }

    public void setEpisodeDAO(EpisodeDAO episodeDAO) {
        this.episodeDAO = episodeDAO;
    }

}
