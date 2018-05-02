package invendolab.katalog.controllers;

import invendolab.katalog.exceptions.CommentNotFoundException;
import invendolab.katalog.models.Likes;
import invendolab.katalog.repositories.LikesRepository;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/likes")
@Api(value ="Likes", description = "All details about the likes. ")
public class LikesController {
    @Autowired
    private LikesRepository repository;

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
    public ResponseEntity<?> saveLikes(@RequestBody Likes like){
        Likes savedLike = repository.save(like);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedLike.getId()).toUri();
        return ResponseEntity.created(location).build();
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
