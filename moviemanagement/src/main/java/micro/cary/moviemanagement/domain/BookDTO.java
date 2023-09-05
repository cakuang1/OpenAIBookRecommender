package micro.cary.moviemanagement.domain;



public class BookDTO {
    private String title;
    private String author;
    private String isbn;
    private String pictureurl;

    public BookDTO() {
    }

    public BookDTO(String title, String author, String isbn, String pictureurl) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.pictureurl = pictureurl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getPictureurl() {
        return pictureurl;
    }

    public void setPictureurl(String pictureurl) {
        this.pictureurl = pictureurl;
    }
}
