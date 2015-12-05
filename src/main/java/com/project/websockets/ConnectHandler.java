package com.project.websockets;

import com.project.websockets.store.WebSocketSessionStore;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

/**
 * Created by jedaka on 05.12.2015.
 */
public class ConnectHandler extends TextWebSocketHandler {


    private WebSocketSessionStore store = new WebSocketSessionStore();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        store.addSession(session);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        store.deleteSession(session);
    }
}
