package micro.cary.moviemanagement.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import micro.cary.moviemanagement.domain.BookDTO;
import micro.cary.moviemanagement.domain.RecommendationDTO;



@RestController
@RequestMapping("/sessions")
public class SessionController {
    private final RestTemplate restTemplate;
    private final String apiUrl; // Replace with your external API URL

    @Autowired
    public SessionController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
        this.apiUrl = "http://recommmendation.com/api"; // Replace with your actual API URL
    }

    @GetMapping("/")
	public List<BookDTO> home(HttpSession session) {
		@SuppressWarnings("unchecked")
		List<BookDTO> messages = (List<BookDTO>) session.getAttribute("books");
		if (messages == null) {
			messages = new ArrayList<>();
		}
		return messages;
	}
    @GetMapping("/getmovierecs")
    public List<RecommendationDTO> getmovies(HttpSession session){
        List<String> movieTitles = new ArrayList<>();
        @SuppressWarnings("unchecked")
        List<BookDTO> books = (List<BookDTO>) session.getAttribute("books");
        if (books != null) {
            for (BookDTO book : books) {
                movieTitles.add(book.getTitle());
            }
        }

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        for (String title : movieTitles) {
            params.add("title", title);
        }

        // Make an HTTP POST request to the external API with request parameters
        ResponseEntity<RecommendationDTO[]> responseEntity = restTemplate.postForEntity(apiUrl, params, RecommendationDTO[].class);

        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            RecommendationDTO[] recommendations = responseEntity.getBody();
            if (recommendations != null && recommendations.length > 0) {
                return Arrays.asList(recommendations);
            }
        }


        //Call external api with movieTitles as input
        return movieTitles;
    }
    @PostMapping("/addbook")
    public List<BookDTO> addBook(@RequestBody BookDTO book, HttpServletRequest request) {
        HttpSession session = request.getSession();
        @SuppressWarnings("unchecked")
        List<BookDTO> books = (List<BookDTO>) session.getAttribute("books");
        
        if (books == null) {
            books = new ArrayList<>();
            session.setAttribute("books", books);
        }
        boolean bookexists = books.stream().anyMatch(existingBook -> existingBook.getIsbn().equals(book.getIsbn()));
        if (!bookexists) {
            books.add(book);
            session.setAttribute("books", books);
         }
        return books;
    }
    
    


    @PostMapping("/deletebook")
    public List<BookDTO> deleteBook(@RequestParam("isbn") String isbn, HttpServletRequest request) {
        @SuppressWarnings("unchecked")
        List<BookDTO> books = (List<BookDTO>) request.getSession().getAttribute("books");    
        if (books != null) {
            books.removeIf(book -> book.getIsbn().equals(isbn));
            request.getSession().setAttribute("books", books);
        }
        return books;
    }
    


}
