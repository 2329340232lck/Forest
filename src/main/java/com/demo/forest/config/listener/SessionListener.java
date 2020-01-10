package com.demo.forest.config.listener;

import com.demo.forest.util.SessionManager;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * 监听器类，此类用来监听session创建与销毁
 */
@WebListener
@Slf4j
public class SessionListener implements HttpSessionListener {
    private static Integer loginUser = 0;

    /**
     * session创建时调用
     */
    @Override
    public void sessionCreated(HttpSessionEvent sessionEvent) {
        HttpSession session = sessionEvent.getSession();
        String sessionId = session.getId();
        loginUser++;
        log.info("Session创建，ID为 = " + sessionId + ",当前在线人数:" + loginUser);
    }

    /**
     * session销毁时调用
     */
    @Override
    public void sessionDestroyed(HttpSessionEvent sessionEvent) {
        HttpSession session = sessionEvent.getSession();
        String sessionId = session.getId();
        loginUser--;
        log.info("Session销毁，ID为 = " + sessionId + "当前在线人数:" + loginUser);
        SessionManager.removeSession(sessionId);
    }
}
