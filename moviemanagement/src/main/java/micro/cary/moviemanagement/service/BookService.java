package micro.cary.moviemanagement.service;

import java.util.ArrayList;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.databind.JsonNode;


import micro.cary.moviemanagement.domain.BookDTO;
import micro.cary.moviemanagement.domain.RecommendationDTO;


@Service
public class BookService {

    private final WebClient webClient;

    @Value("${api.key}")
    private String apiKey;

    public BookService() {
        this.webClient = WebClient.builder()
                .baseUrl("https://www.googleapis.com/books/v1")
                .build();

    }

    public JsonNode searchBook(String title, String author) {
        System.out.println(title);
        System.out.println(author);
        return webClient
                .get()
                .uri("/volumes?q=intitle:{title}+inauthor:{author}", title, author)
                .retrieve()
                .bodyToMono(JsonNode.class)
                .block();
    }


    public List<BookDTO> fetchBooks() {
        return webClient.get()
                .uri("/volumes?q=test&maxResults=4&key=" + apiKey)
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



    public static RecommendationDTO proccesItemRecc(JsonNode response) { 
        JsonNode item = response.get("items").get(0);
        String title = item.path("volumeInfo").path("title").asText();
        String author = item.path("volumeInfo").path("authors").get(0).asText();
        String pictureUrl = "";
        String description = item.path("volumeInfo").path("description").asText();
        JsonNode imageLinks = item.path("volumeInfo").path("imageLinks");
        if (imageLinks.has("thumbnail")) {
            pictureUrl = imageLinks.path("thumbnail").asText();
        }
        return new RecommendationDTO(title, author, author, pictureUrl, "" , description);



        


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



