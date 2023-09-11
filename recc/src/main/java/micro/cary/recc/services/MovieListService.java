package micro.cary.recc.services;

import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;



@Service
public class MovieListService {
    private final WebClient.Builder webClientBuilder;

    public MovieListService(WebClient.Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder;
    }
    public List<String> getMovies(String sessionId) {
        return webClientBuilder
            .baseUrl("http://localhost:8080") // Replace with your actual API base URL
            .defaultHeader(HttpHeaders.COOKIE, "JSESSIONID=" + sessionId) // Set the session ID in the headers
            .build()
            .get()
            .uri("/sessions/getmovies") // Replace with the actual API endpoint to get movies
            .retrieve()
            .bodyToFlux(String.class) // Deserialize as a Flux of strings
            .collectList() // Collect the Flux into a List
            .block();
    }
}

