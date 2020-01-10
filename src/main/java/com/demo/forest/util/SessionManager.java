package com.demo.forest.util;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class SessionManager {

    private static Map<String, Object> sessionMap = new HashMap<>();

    public synchronized static void setSession(String sessionId, Object value) {
        sessionMap.put(sessionId, value);
        log.info("加入Session,ID = " + sessionId);
    }

    public synchronized static Object getSession(String key) {
        if (sessionMap.containsKey(key)) {
            return sessionMap.get(key);
        } else {
            return null;
        }
    }

    public synchronized static void removeSession(String sessionId) {
        if (sessionMap.containsKey(sessionId)) {
            sessionMap.remove(sessionId);
            log.info("移除Session,ID = " + sessionId);
        } else {
            log.error("Session不存在，移除失败");
        }
    }
}
