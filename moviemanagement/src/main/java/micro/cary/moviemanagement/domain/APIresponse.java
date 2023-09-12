package micro.cary.moviemanagement.domain;



public class APIresponse {
    private String title;
    private String author;
    private String reason;

    // Constructors
    public APIresponse () {
    }

    public APIresponse(String title, String author, String reason) {
        this.title = title;
        this.author = author;
        this.reason = reason;
    }

    // Getters and setters
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

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}   