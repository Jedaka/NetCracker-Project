package com.project.websockets.store;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by jedaka on 05.12.2015.
 */
@Component
public class WebSocketSessionStore {

    private List<WebSocketSession> sessions = new LinkedList<>();

    public void addSession(WebSocketSession session){
        synchronized (sessions){
            sessions.add(session);
        }
    }

    public List<WebSocketSession> getSessions(){
        List<WebSocketSession> resultList = new LinkedList<>();
        synchronized (sessions) {
            Collections.copy(resultList, sessions);
        }
        return resultList;
    }

    public void deleteSession(WebSocketSession session){
        synchronized (sessions){
            sessions.remove(session);
        }
    }


}
