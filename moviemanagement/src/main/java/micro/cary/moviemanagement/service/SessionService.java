package micro.cary.moviemanagement.service;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import micro.cary.moviemanagement.domain.Book;
import micro.cary.moviemanagement.domain.Sessions;
import micro.cary.moviemanagement.repository.SessionRepository;




@Service
public class SessionService {
    

    private final SessionRepository repository;

    @Autowired
    public SessionService(SessionRepository repository) {
        this.repository = repository;
    }

    public HashMap<String,Book> getallBooks(String sessionid) {
        Sessions currSession = repository.findById(sessionid).orElse(new Sessions(sessionid));
        return currSession.getBooks();

    }
    public void addBookSession(String sessionid,String bookisbn,Book book) {
        Sessions currentSession = repository.findById(sessionid).orElse(new Sessions(sessionid));
        currentSession.getBooks().put(bookisbn, book);  
    }
    public void deleteBookSession(String sessionid,String bookisbn) {
        Sessions currentSession = repository.findById(sessionid).orElse(null);
        if (currentSession != null) {
            currentSession.getBooks().remove(bookisbn);
            repository.save(currentSession); 
        }
    }
}
