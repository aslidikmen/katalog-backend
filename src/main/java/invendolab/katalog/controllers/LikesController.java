package invendolab.katalog.controllers;

import com.fasterxml.jackson.annotation.JsonIgnore;
import invendolab.katalog.exceptions.CommentNotFoundException;
import invendolab.katalog.models.Consumer;
import invendolab.katalog.models.Likes;
import invendolab.katalog.repositories.ConsumerRepository;
import invendolab.katalog.repositories.LikesRepository;
import invendolab.katalog.repositories.ProductRepository;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/likes")
@Api(value ="Likes", description = "All details about the likes. ")
public class LikesController {
    @Autowired
    private LikesRepository repository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ConsumerRepository consumerRepository;

    @GetMapping(value = "/all")
    public List<Likes> getAllLikes(){
        return repository.findAll();
    }

    @GetMapping(value = "/{id}")
    public Likes getById(@PathVariable long id){
        Optional<Likes> like = repository.findById(id);

        if (!like.isPresent())
            throw new CommentNotFoundException("id-" + id);

        return like.get();
    }

    @PostMapping(value = "/createLike")
    public Likes saveLikes(@RequestParam (value = "product_id") Long productId, @RequestParam (value = "consumer_id") Long consumerId, @Valid @RequestBody Likes like){
        return (productRepository.findById(productId)).map(product -> {
            like.setProduct(product);

            /*if (!consumerRepository.existsById(consumerId)) {
                throw new CommentNotFoundException("product Id " + productId + " not found");
            } else {
                like.getConsumers().add((consumerRepository.findById(consumerId).get()));
            } */

            return repository.save(like);
        }).orElseThrow(() -> new CommentNotFoundException("product Id " + productId + " not found"));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Object> updateById(@RequestBody Likes like, @PathVariable long id){

        Optional<Likes> likesOptional = repository.findById(id);

        if (!likesOptional.isPresent())
            return ResponseEntity.notFound().build();

        like.setId(id);

        repository.save(like);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/{id}")
    public void deleteLike(@PathVariable long id){
        repository.deleteById(id);
    }
}
