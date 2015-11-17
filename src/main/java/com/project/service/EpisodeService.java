package com.project.service;

import com.project.database.dao.EpisodeDAO;
import com.project.model.Episode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by jedaka on 17.11.2015.
 */
@Service
@Transactional
public class EpisodeService {

    @Autowired
    private EpisodeDAO episodeDAO;

    public void save(Episode episode){
        this.episodeDAO.create(episode);
    }

    public void setEpisodeDAO(EpisodeDAO episodeDAO) {
        this.episodeDAO = episodeDAO;
    }
}
