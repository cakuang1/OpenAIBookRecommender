package micro.cary.moviemanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.databind.JsonNode;

import micro.cary.moviemanagement.service.BookService;
import reactor.core.publisher.Mono;

import java.util.List;


@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }



    @GetMapping("/fetch")
    public ResponseEntity<List<JsonNode>> fetchBooks() {
        List<JsonNode> books = bookService.fetchBooks().block(); 
        return ResponseEntity.ok(books);
    }
}
