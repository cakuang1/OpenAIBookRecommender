package micro.cary.moviemanagement.domain;

import java.util.List;



public class Book {
    private String isbn; // Used as the ID
    private String name;
    private List<String> genres;

    public Book(String isbn, String name, List<String> genres) {
        this.isbn = isbn;
        this.name = name;
        this.genres = genres;
    }
    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }
}
