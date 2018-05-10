package invendolab.katalog.controllers;

import invendolab.katalog.exceptions.ProductNotFoundException;
import invendolab.katalog.models.Product;
import invendolab.katalog.repositories.ProductRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/product")
@Api(value ="Products", description = "All details about the products. ")
public class ProductController {

    @Autowired
    private ProductRepository repository;

    @ApiOperation(value = "Returns all products")
    @GetMapping(value = "/all")
    public List<Product> getAllProducts(@RequestParam(value = "isActive") Boolean isActive){
        if (isActive) {
            return repository.findAllByisActiveIsTrue();
        }else {
            return repository.findAllByisActiveIsFalse();
        }
    }

    @ApiOperation(value = "Returns all products by sorting with active products")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "isActive", type = "Boolean", paramType = "query"),
            @ApiImplicitParam(name = "sortBy", value = "1: id, 2: title, 3: upvotecount, 4: downvotecount, 5: price", type="Integer", allowableValues = "1, 2, 3, 4, 5", paramType = "query"),
            @ApiImplicitParam(name = "order", allowableValues = "DESC, ASC", paramType = "query")
    })
    @GetMapping(value = "/all/sort")
    public List<Product> getAllProductsBySorting(@RequestParam(value = "sortBy") Integer sortBy, @RequestParam(value = "order") String order) {

        List<Product> productList;

        if (sortBy != null && (order != null && (order.equals("ASC") || order.equals("DESC")))) {

            switch (sortBy) {
                case 1:
                    productList = repository.findAllByisActiveIsTrueOrderByIdAsc();
                    break;
                case 2:
                    productList = repository.findAllByisActiveIsTrueOrderByTitleAsc();
                    break;
                case 3:
                    productList = repository.findAllByisActiveIsTrueOrderByUpVoteCountAsc();
                    break;
                case 4:
                    productList = repository.findAllByisActiveIsTrueOrderByDownVoteCountAsc();
                    break;
                case 5:
                    productList = repository.findAllByisActiveIsTrueOrderByPriceAsc();
                    break;
                default:
                    productList = repository.findAll();
                    break;
            }

        } else if(sortBy != null) {

            switch (sortBy) {
                case 1:
                    productList = repository.findAllByisActiveIsTrueOrderByIdDesc();
                    break;
                case 2:
                    productList = repository.findAllByisActiveIsTrueOrderByTitleDesc();
                    break;
                case 3:
                    productList = repository.findAllByisActiveIsTrueOrderByUpVoteCountDesc();
                    break;
                case 4:
                    productList = repository.findAllByisActiveIsTrueOrderByDownVoteCountDesc();
                    break;
                case 5:
                    productList = repository.findAllByisActiveIsTrueOrderByPriceDesc();
                    break;
                default:
                    productList = repository.findAll();
                    break;
            }

        } else {
            productList = repository.findAll();
        }

        return productList;

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