package micro.cary.moviemanagement.utils;

import java.util.Map;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import java.util.concurrent.TimeUnit;



@Component
public class RedisSessionManager {
    private final RedisTemplate<String, String> redisTemplate;
    private final HashOperations<String, String, String> hashOperations;

    public RedisSessionManager(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
        this.hashOperations = redisTemplate.opsForHash();
    }

    public void storeSession(String sessionId, Map<String, String> sessionData, int sessionDurationInSeconds) {
        // Store the session data as a hash
        hashOperations.putAll(sessionId, sessionData);

        // Set the expiration time (TTL) for the session key
        redisTemplate.expire(sessionId, sessionDurationInSeconds, TimeUnit.SECONDS);
    }

    public Map<String, String> getSessionData(String sessionId) {
        // Retrieve the session data hash from Redis
        return hashOperations.entries(sessionId);
    }
    
    public void addKeyToSession(String sessionId, String key, String value) {
        hashOperations.put(sessionId, key, value);
        }
    
    public void deleteKeyFromSession(String sessionId, String key) {
            hashOperations.delete(sessionId, key);
        }

}