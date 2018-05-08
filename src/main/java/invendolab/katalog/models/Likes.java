package invendolab.katalog.models;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Likes implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String createdTime;
    private String updatedTime;
    private Boolean isUpVote;


    @ManyToOne(cascade=CascadeType.ALL, targetEntity = Product.class)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    @JoinColumn(name="product_id")
    private Product product;

    @ManyToOne(cascade=CascadeType.ALL, targetEntity=Consumer.class)
    @JoinColumn(name="consumer_id")
    private Consumer consumer;

    public Likes(){

    }

    public Likes(String createdTime, String updatedTime, Boolean isUpVote, Product product, Consumer consumer) {
        this.createdTime = createdTime;
        this.updatedTime = updatedTime;
        this.isUpVote = isUpVote;
        this.product = product;
        this.consumer = consumer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Consumer getConsumer() {
        return consumer;
    }

    public void setConsumer(Consumer consumer) {
        this.consumer = consumer;
    }

    public Boolean getUpVote() {
        return isUpVote;
    }

    public void setUpVote(Boolean upVote) {
        isUpVote = upVote;
    }
}
