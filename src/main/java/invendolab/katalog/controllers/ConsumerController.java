package invendolab.katalog.controllers;

import invendolab.katalog.exceptions.UserNotFoundException;
import invendolab.katalog.helpers.Response;
import invendolab.katalog.models.Consumer;
import invendolab.katalog.repositories.ConsumerRepository;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

    private Response response = new Response();

    @PostMapping(value = "/admin/addCredential")
    public ResponseEntity<?> addAdmin(){
        try {

            if (!repository.existsByEmail("catalogAdmin")) {

                    repository.save(new Consumer("catalogAdmin", "102030", "catalogAdmin", "admin", "admin", "admin", "admin", "admin", true, new String[]{"ROLE_ADMIN"}));

            } else {

                response.setErrorCode(406);
                response.setMessage("Bu e-mail adresine sahip bir admin var zaten!");
                response.setSuccess(false);
                return new ResponseEntity<>(response, HttpStatus.NOT_ACCEPTABLE);

            }

            response.setErrorCode(200);
            response.setMessage("Admin eklendi.");
            response.setSuccess(true);
            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {

            response.setErrorCode(500);
            response.setMessage("Admin eklenemedi.");
            response.setSuccess(false);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @PreAuthorize("hasRole('ADMIN')")
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

    @PostMapping(value = "/register")
    public ResponseEntity<Object> createConsumer(@RequestBody Consumer consumer){
        consumer.setRoles(new String[]{"ROLE_USER"});
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
