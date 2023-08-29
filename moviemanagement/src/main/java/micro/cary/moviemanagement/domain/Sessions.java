package micro.cary.moviemanagement.domain;

import java.util.HashMap;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
@RedisHash("Session")
public class Sessions {
    @Id
    private String sessionId;
    private HashMap<String,String> books = new HashMap();

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public HashMap<String, String> getBooks() {
        return books;
    }

    public void setBooks(HashMap<String, String> books) {
        this.books = books;
    }
}