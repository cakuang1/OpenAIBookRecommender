package micro.cary.moviemanagement.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import micro.cary.moviemanagement.domain.Book;
import micro.cary.moviemanagement.domain.Sessions;
import micro.cary.moviemanagement.repository.SessionRepository;




@Service
public class SessionService {
    
    @Autowired
    private SessionRepository repository;

    public void addBooktoSession(String sessionid,String bookisbn,Book book) {
        Sessions currentSession = repository.findById(sessionid).orElse(new Sessions(sessionid));
        currentSession.getBooks().put(bookisbn, book);
    }
    public void ad
}
