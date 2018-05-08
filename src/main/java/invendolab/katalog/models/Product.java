package invendolab.katalog.models;

import javax.persistence.*;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private Float price;
    @Column(name = "description", columnDefinition = "text", length = 20000)
    private String description;
    private String type;
    private String imageUrl;
    private String difficulty;
    private String url;
    private Integer upVoteCount;
    private Integer downVoteCount;
    private boolean isActive;
    private String createdTime;
    private String updatedTime;
    private String tags;
    private Integer commentCount;

    public Product(){ }

    public Product(String title, Float price, String description, String type, String imageUrl, String difficulty, String url, Integer upVoteCount, Integer downVoteCount, boolean isActive, String createdTime, String updatedTime, String tags, Integer commentCount) {
        this.title = title;
        this.price = price;
        this.description = description;
        this.type = type;
        this.imageUrl = imageUrl;
        this.difficulty = difficulty;
        this.url = url;
        this.upVoteCount = upVoteCount;
        this.downVoteCount = downVoteCount;
        this.isActive = isActive;
        this.createdTime = createdTime;
        this.updatedTime = updatedTime;
        this.tags = tags;
        this.commentCount = commentCount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getUpVoteCount() {
        return upVoteCount;
    }

    public void setUpVoteCount(Integer upVoteCount) {
        this.upVoteCount = upVoteCount;
    }

    public Integer getDownVoteCount() {
        return downVoteCount;
    }

    public void setDownVoteCount(Integer downVoteCount) {
        this.downVoteCount = downVoteCount;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public String getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(String updatedTime) {
        this.updatedTime = updatedTime;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public Integer getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }
}
