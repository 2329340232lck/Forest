package com.demo.forest.config.webSocket;

import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
@ServerEndpoint("/webSocket/{userId}")
public class WebSocketController {

    private static Map<String, Session> sessionMap = new HashMap<>();

    @OnOpen
    public void onOpen(Session session, @PathParam("userId") String userId) {
        System.out.println("用户已连接:" + userId);
        sessionMap.put(userId, session);
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        System.out.println("session = " + session);
        System.out.println("message = " + message);
    }

    @OnError
    public void onError(Session session, Throwable error) {
        error.printStackTrace();
        System.err.println("session = " + session);
    }

    @OnClose
    public void onClose(Session session, @PathParam("userId") String userId) {
        try {
            session.close();
            sessionMap.remove(userId);
            System.out.println("=================>> 用户:" + userId + "退出连接 <<===============");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //推送消息
    public static void sendMessage(String message, String userId) throws IOException {
        if (sessionMap.containsKey(userId)) {
            RemoteEndpoint.Basic basicRemote = sessionMap.get(userId).getBasicRemote();
            basicRemote.sendText(message);
        } else {
            System.err.println("所选用户不存在或已离线");
        }
    }
}
