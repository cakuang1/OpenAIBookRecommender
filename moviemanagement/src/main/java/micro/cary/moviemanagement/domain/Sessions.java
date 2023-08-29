package micro.cary.moviemanagement.domain;
import java.util.HashMap;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;



@RedisHash("Session")
public class Sessions {
    @Id
    private String sessionId;
    private HashMap<String,Book> books = new HashMap<String,Book>();


    public Sessions(String sessionId) {
        this.sessionId = sessionId;
    }
    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public HashMap<String, Book> getBooks() {
        return books;
    }

    public void setBooks(HashMap<String, Book> books) {
        this.books = books;
    }
}