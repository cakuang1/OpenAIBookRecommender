package micro.cary.moviemanagement.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.databind.JsonNode;

import reactor.core.publisher.Mono;

@Service
public class BookService {

    private final WebClient webClient;


    public BookService() {
        this.webClient = WebClient.builder()
                .baseUrl("https://www.googleapis.com/books/v1")
                .build();

    }

    public Mono<List<JsonNode>> fetchBooks() {
        return webClient.get()
                .uri("/volumes?q=test&maxResults=4&key=AIzaSyCOeK3OAiQpxV7CaTPE-FAhDdI0fAFrzSA")
                .retrieve()
                .bodyToMono(JsonNode.class)
                .map(response -> {
                    JsonNode itemsNode = response.get("items");
                    List<JsonNode> itemList = new ArrayList<>();
                    if (itemsNode.isArray()) {
                        itemsNode.forEach(itemList::add);
                    }
                    return itemList;
                });
    }
}



