package micro.cary.moviemanagement.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import micro.cary.moviemanagement.domain.Book;
import micro.cary.moviemanagement.domain.Sessions;
import micro.cary.moviemanagement.repository.SessionRepository;

public class ServiceTest {
    

    @Mock
    private SessionRepository repository;

    private SessionService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        service = new SessionService(repository);
    }




    @Test
    void testGetAll() {
        String sessionId = "testsession123";
        Book lightningThiefBook = new Book("test", "lightning thief", Arrays.asList("genre1", "genre2"));
        Sessions existingSession = new Sessions(sessionId);
        existingSession.getBooks().put("book1", lightningThiefBook);
        when(repository.findById(sessionId)).thenReturn(Optional.of(existingSession));
        HashMap<String, Book> books = service.getallBooks(sessionId);

        assertNotNull(books);
        assertEquals(1, books.size());

    }
    @Test
    void testAddBooks() {
        String sessionId = "testsession123";
        Book lightningThiefBook = new Book("test", "lightning thief", Arrays.asList("genre1", "genre2"));

        Sessions existingSession = new Sessions(sessionId);

        when(repository.findById(sessionId)).thenReturn(Optional.of(existingSession));

        service.addBookSession(sessionId, "book", lightningThiefBook);
        
        assertEquals(1, existingSession.getBooks().size());

    }
    @Test
    void testDeleteBooks() {
        String sessionId = "testsession123";
        Book lightningThiefBook = new Book("test", "lightning thief", Arrays.asList("genre1", "genre2"));

        Sessions existingSession = new Sessions(sessionId);

        when(repository.findById(sessionId)).thenReturn(Optional.of(existingSession));

        service.addBookSession(sessionId, "book", lightningThiefBook);
        
        assertEquals(1, existingSession.getBooks().size());

    }

}
