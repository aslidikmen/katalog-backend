package invendolab.katalog.controllers;

import invendolab.katalog.exceptions.ProductNotFoundException;
import invendolab.katalog.models.Product;
import invendolab.katalog.helpers.Response;
import invendolab.katalog.repositories.ProductRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/product")
@Api(value ="Products", description = "All details about the products. ")
public class ProductController {
    private Response response = new Response();

    @Autowired
    private ProductRepository repository;

    @ApiOperation(value = "Returns all products")
    @GetMapping(value = "/all")
    public List<Product> getAllProducts(@RequestParam(value = "isActive") Boolean isActive){
        if (isActive) {
            return repository.findAll();
        }else {
            return repository.findAllByisActiveIsFalse();
        }
    }

    @GetMapping(value = "/{id}")
    public Product getById(@PathVariable long id){
        Optional<Product> product = repository.findById(id);

        if (!product.isPresent())
            throw new ProductNotFoundException("id-" + id);

        return product.get();
    }

    @ApiOperation(value = "Save product")
    @PostMapping(value = "/createProduct")
    public ResponseEntity<?> saveProduct(@RequestBody Product product){
        Product savedProduct = repository.save(product);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedProduct.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping(value = "/{id}")
    public void deleteProduct(@PathVariable long id){
        repository.deleteById(id);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Object> updateById(@RequestBody Product product, @PathVariable long id){

        Optional<Product> studentOptional = repository.findById(id);

        if (!studentOptional.isPresent())
            return ResponseEntity.notFound().build();

        product.setId(id);

        repository.save(product);

        return ResponseEntity.noContent().build();
    }

}