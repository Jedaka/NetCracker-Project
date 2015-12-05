package com.project.websockets;

import com.project.websockets.store.WebSocketSessionStore;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

/**
 * Created by jedaka on 05.12.2015.
 */
public class ConnectHandler extends TextWebSocketHandler {


    @Autowired
    private WebSocketSessionStore sessionStore;
    private Logger logger = Logger.getLogger(ConnectHandler.class);

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        logger.info("New WebSocket connection! " + session.getRemoteAddress());
        sessionStore.addSession(session);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        logger.info("WebSocket connection was lost!");
        sessionStore.deleteSession(session);
    }
}
