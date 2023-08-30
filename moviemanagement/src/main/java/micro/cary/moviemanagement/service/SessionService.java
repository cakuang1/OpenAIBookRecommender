package micro.cary.moviemanagement.service;



import org.springframework.stereotype.Service;

import micro.cary.moviemanagement.utils.RedisSessionManager;
import java.util.Map;


@Service
public class SessionService {

    private final RedisSessionManager sessionManager;


    public SessionService(RedisSessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    public void storeSessionData(String sessionId, Map<String, String> sessionData, int sessionDurationInSeconds) {
        sessionManager.storeSession(sessionId, sessionData, sessionDurationInSeconds);
    }

    public Map<String, String> getSessionData(String sessionId) {
        return sessionManager.getSessionData(sessionId);
    }
    
    public void addKeyToSessionData(String sessionId, String key, String value) {
        sessionManager.addKeyToSession(sessionId, key, value);
    }

    public void deleteKeyFromSessionData(String sessionId, String key) {
        sessionManager.deleteKeyFromSession(sessionId, key);
    }
}



