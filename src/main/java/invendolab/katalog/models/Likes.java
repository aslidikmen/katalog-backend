package invendolab.katalog.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import invendolab.katalog.repositories.ProductRepository;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Likes implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String createdTime;
    private String updatedTime;


    @ManyToOne(cascade=CascadeType.ALL, targetEntity = Product.class)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    @JoinColumn(name="product_id")
    private Product product;


    //ONE TO MANY RELATION TO CONSUMER
    /*@OneToMany(cascade=CascadeType.ALL, targetEntity=Consumer.class)
    @JoinColumn(name="consumer_id")
    private Set<Consumer> consumerSet = new HashSet<>(); */

    public Likes(){

    }

    public Likes(String createdTime, String updatedTime) {
        this.createdTime = createdTime;
        this.updatedTime = updatedTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
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

}
