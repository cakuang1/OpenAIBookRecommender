package micro.cary.moviemanagement.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import micro.cary.moviemanagement.domain.BookDTO;
import micro.cary.moviemanagement.service.BookService;

import java.util.List;





@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }
    @GetMapping("/fetch")
    public ResponseEntity<List<BookDTO>> fetchBooks() {
        List<BookDTO> books = bookService.fetchBooks();
        return ResponseEntity.ok(books);
    }
    
}
