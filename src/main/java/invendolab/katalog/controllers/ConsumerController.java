package invendolab.katalog.controllers;

import invendolab.katalog.exceptions.UserNotFoundException;
import invendolab.katalog.models.Consumer;
import invendolab.katalog.repositories.ConsumerRepository;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/consumer")
@Api(value ="Consumer", description = "All details about the consumers. ")
public class ConsumerController {

    @Autowired
    private ConsumerRepository repository;

    @ApiOperation(value = "A list of all consumers", response = Consumer.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 404, message = "There is no user in database")
    })
    @GetMapping(value = "/all")
    public List<Consumer> getAllConsumers(){
        return repository.findAll();
    }

    @GetMapping(value = "/{id}")
    public Consumer getById(@PathVariable long id){
        Optional<Consumer> student = repository.findById(id);

        if (!student.isPresent())
            throw new UserNotFoundException("id-" + id);

        return student.get();
    }

    @PostMapping(value = "/createConsumer")
    public ResponseEntity<Object> createConsumer(@RequestBody Consumer consumer){
        Consumer savedUSer = repository.save(consumer);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedUSer.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Object> updateById(@RequestBody Consumer consumer, @PathVariable long id){

        Optional<Consumer> studentOptional = repository.findById(id);

        if (!studentOptional.isPresent())
            return ResponseEntity.notFound().build();

        consumer.setId(id);

        repository.save(consumer);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/{id}")
    public void deleteConsumer(@PathVariable long id){
        repository.deleteById(id);
    }

}
