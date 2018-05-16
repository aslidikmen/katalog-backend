package invendolab.katalog.models;

import javax.persistence.*;

@Entity
public class CommentReview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String pros;
    private String cons;

    @Column(name = "description", columnDefinition = "text", length = 20000)
    private String content;

    private boolean isActive;
    private String createdTime;
    private String updatedTime;

    @ManyToOne
    private Product product;

    public CommentReview(){

    }

    public CommentReview(String pros, String cons, String content, boolean isActive, String createdTime, String updatedTime, Product product) {
        this.pros = pros;
        this.cons = cons;
        this.content = content;
        this.isActive = isActive;
        this.createdTime = createdTime;
        this.updatedTime = updatedTime;
        this.product = product;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPros() {
        return pros;
    }

    public void setPros(String pros) {
        this.pros = pros;
    }

    public String getCons() {
        return cons;
    }

    public void setCons(String cons) {
        this.cons = cons;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

}
