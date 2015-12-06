package com.project.websockets.store;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by jedaka on 05.12.2015.
 *
 * Store of websockets connections (Online users)
 */
@Component
public class WebSocketSessionStore {

    private static List<WebSocketSession> sessions = new LinkedList<>();

    public void addSession(WebSocketSession session){
        synchronized (sessions){
            sessions.add(session);
        }
    }

    public List<WebSocketSession> getSessions() {
        return sessions;
    }

    public void deleteSession(WebSocketSession session){
        synchronized (sessions){
            sessions.remove(session);
        }
    }


}
