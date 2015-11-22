package com.project.service;

import com.project.database.dao.EpisodeDAO;
import com.project.model.Episode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by jedaka on 17.11.2015.
 */
@Service
@Transactional
public class EpisodeService {

    @Autowired
    private EpisodeDAO episodeDAO;

    public int save(Episode episode) throws Exception{
        return this.episodeDAO.create(episode);
    }

    @Transactional(readOnly = true)
    public List<Episode> getAll() throws Exception{
        return this.episodeDAO.getAll();
    }

    public void setEpisodeDAO(EpisodeDAO episodeDAO) {
        this.episodeDAO = episodeDAO;
    }
}
