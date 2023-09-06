package micro.cary.moviemanagement.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import micro.cary.moviemanagement.domain.BookDTO;





@RestController
public class SessionController {
    @GetMapping("/")
	public List<BookDTO> home(HttpSession session) {
		@SuppressWarnings("unchecked")
		List<BookDTO> messages = (List<BookDTO>) session.getAttribute("books");
		if (messages == null) {
			messages = new ArrayList<>();
		}
		return messages;
	}





    @PostMapping("/addbook")
    public List<BookDTO> addBook(@RequestBody BookDTO book, HttpServletRequest request) {
        @SuppressWarnings("unchecked")
        List<BookDTO> books = (List<BookDTO>) request.getSession().getAttribute("books");
        
        if (books == null) {
            books = new ArrayList<>();
            request.getSession().setAttribute("books", books);
        }
        
        books.add(book);
        request.getSession().setAttribute("books", books);
        
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
