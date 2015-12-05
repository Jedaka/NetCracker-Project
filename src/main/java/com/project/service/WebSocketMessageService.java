package com.project.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.model.Episode;
import com.project.websockets.store.WebSocketSessionStore;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.List;

/**
 * Created by jedaka on 05.12.2015.
 */
@Service
public class WebSocketMessageService {

    @Autowired
    private WebSocketSessionStore sessionStore;

    private ObjectMapper objectMapper = new ObjectMapper();
    private Logger logger = Logger.getLogger(WebSocketMessageService.class);

    public void sendEpisodeToConnectedUsers(Episode episode) {
        logger.info("Sending episode to users...");
        List<WebSocketSession> sessions = sessionStore.getSessions();
        logger.info("Have " + sessions.size() + " web socket connection[s]");
        synchronized (sessions) {
            for (WebSocketSession session: sessions){
                try {
                    String json = objectMapper.writeValueAsString(episode);
                    session.sendMessage(new TextMessage(json));
                    logger.info("Episode has been successfully sent to " + session.getRemoteAddress());
                } catch (IOException e) {
                    logger.warn("An error has occurred while sending episode to " + session.getRemoteAddress() + " " + e.getMessage());
                    continue;
                }

            }
        }
    }

    public void setSessionStore(WebSocketSessionStore sessionStore) {
        this.sessionStore = sessionStore;
    }
}
