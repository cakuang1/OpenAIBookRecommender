package micro.cary.moviemanagement.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;



import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import micro.cary.moviemanagement.domain.APIresponse;
import micro.cary.moviemanagement.domain.BookDTO;
import micro.cary.moviemanagement.domain.RecommendationDTO;
import micro.cary.moviemanagement.service.BookService;



@RestController
@RequestMapping("/sessions")
public class SessionController {
    private final RestTemplate restTemplate;
    private final BookService bookService;


    public SessionController(RestTemplate restTemplate,BookService bookService) {
        this.restTemplate = restTemplate;
        this.bookService = bookService;
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
        String listOfMovies = String.join(",", movieTitles);
        System.out.println(listOfMovies);
        String apiUrlWithParams = "http://recc:8081/recommendation/grabmovies?listofmovies=" + listOfMovies;
        ResponseEntity<APIresponse[]> responseEntity = restTemplate.getForEntity(apiUrlWithParams, APIresponse[].class);
        List<APIresponse> apiResponses = new ArrayList<>();
        System.out.println(responseEntity.getStatusCode());
        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            APIresponse[] apiResponsesArray = responseEntity.getBody();
            if (apiResponsesArray != null) {
                apiResponses.addAll(Arrays.asList(apiResponsesArray));
            }
        }
        List<RecommendationDTO> returnlist = new ArrayList<>();
        for (APIresponse apIresponse : apiResponses) {
            JsonNode curr = bookService.searchBook(apIresponse.getTitle(), apIresponse.getAuthor());
            System.out.println(curr);
            Integer totalitems = curr.get("totalItems").asInt();
            if (totalitems > 0) {
                RecommendationDTO recc = BookService.proccesItemRecc(curr);
                recc.setReason(apIresponse.getReason());
                returnlist.add(recc);
            }
        }
        return returnlist;
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
