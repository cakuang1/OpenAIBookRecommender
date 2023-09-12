package micro.cary.moviemanagement.domain;

import java.io.Serializable;

public class RecommendationDTO implements Serializable{
    private String title;
    private String author;
    private String description;
    private String pictureUrl;
    private String reason;

    // Default constructor
    public RecommendationDTO() {
    }

    // Constructor with all fields
    public RecommendationDTO(String title, String author, String isbn, String pictureUrl, String reason,String description) {
        this.title = title;
        this.author = author;
        this.pictureUrl = pictureUrl;
        this.reason = reason;
        this.description = description;
    }

    // Getter and Setter methods for each field
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

    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
