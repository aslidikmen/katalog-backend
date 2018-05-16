package invendolab.katalog.controllers;

import invendolab.katalog.exceptions.CommentNotFoundException;
import invendolab.katalog.models.CommentReview;
import invendolab.katalog.repositories.CommentReviewRepository;
import invendolab.katalog.repositories.ProductRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/commentReview")
@Api(value ="Comments", description = "All details about the comments and reviews. ")
public class CommentReviewController {
    @Autowired
    private CommentReviewRepository repository;

    @Autowired
    private ProductRepository productRepository;

    @GetMapping(value = "/all")
    public List<CommentReview> getAllCommentReviews(){
        return repository.findAll();
    }

    @GetMapping(value = "/all/byProductId/{id}")
    public  List<CommentReview> getAllCommentsByProductId(@PathVariable Long productId) {
        return repository.findAllByProductId(productId);
    }

    @GetMapping(value = "/{id}")
    public CommentReview getById(@PathVariable long id){
        Optional<CommentReview> commentReview = repository.findById(id);

        if (!commentReview.isPresent())
            throw new CommentNotFoundException("id-" + id);

        return commentReview.get();
    }

    @PostMapping(value = "/createComment")
    public ResponseEntity<?> saveCommentReview(@RequestBody CommentReview commentReview, @RequestParam(name = "product_id") Long productId){
        commentReview.setProduct(productRepository.findById(productId).get());
        CommentReview savedCommentReview = repository.save(commentReview);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedCommentReview.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Object> updateById(@RequestBody CommentReview commentReview, @PathVariable long id){

        Optional<CommentReview> commentReviewOptional = repository.findById(id);

        if (!commentReviewOptional.isPresent())
            return ResponseEntity.notFound().build();

        commentReview.setId(id);

        repository.save(commentReview);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/{id}")
    public void deleteCommentReview(@PathVariable long id){
        repository.deleteById(id);
    }
}
