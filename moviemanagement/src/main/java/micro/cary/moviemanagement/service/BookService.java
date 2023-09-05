package micro.cary.moviemanagement.service;

import java.util.ArrayList;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.databind.JsonNode;


import micro.cary.moviemanagement.domain.BookDTO;

@Service
public class BookService {

    private final WebClient webClient;


    public BookService() {
        this.webClient = WebClient.builder()
                .baseUrl("https://www.googleapis.com/books/v1")
                .build();

    }

    public List<BookDTO> fetchBooks() {
        return webClient.get()
                .uri("/volumes?q=test&maxResults=11")
                .retrieve()
                .bodyToMono(JsonNode.class)
                .map(this::processBooksResponse)
                .block(); 
    }

    private List<BookDTO> processBooksResponse(JsonNode response) {
        List<BookDTO> books = new ArrayList<>();
        JsonNode itemsNode = response.get("items");
        if (itemsNode != null && itemsNode.isArray()) {
            for (JsonNode item : itemsNode) {
                // Process each item and create Book objects
                BookDTO book = processItem(item);
                books.add(book);
            }
        }
        return books;
    }

    private BookDTO processItem(JsonNode item) {
        // Extract the keys you need from the item JsonNode
        String title = item.path("volumeInfo").path("title").asText();
        String author = item.path("volumeInfo").path("authors").get(0).asText();
        String isbn13 = "";
        String pictureUrl = "";
    
        // Extract ISBN-13 and picture URL if available
        JsonNode identifiers = item.path("volumeInfo").path("industryIdentifiers");
        for (JsonNode identifier : identifiers) {
            String type = identifier.path("type").asText();
            if ("ISBN_13".equals(type)) {
                isbn13 = identifier.path("identifier").asText();
            }
        }
    
        JsonNode imageLinks = item.path("volumeInfo").path("imageLinks");
        if (imageLinks.has("thumbnail")) {
            pictureUrl = imageLinks.path("thumbnail").asText();
        }
    
        // Create a new JsonNode with the extracted keys
        return new BookDTO(title, author, isbn13, pictureUrl);
        // Add more extracted keys to the new JsonNode
    }
    
}



