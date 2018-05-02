package invendolab.katalog.controllers;

import invendolab.katalog.exceptions.CommentNotFoundException;
import invendolab.katalog.models.Asset;
import invendolab.katalog.repositories.AssetsRepository;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/assets")
@Api(value ="Asset", description = "All details about the assets. ")
public class AssetsController {
    @Autowired
    private AssetsRepository repository;

    @GetMapping(value = "/all")
    public List<Asset> getAllAssets(){
        return repository.findAll();
    }

    @GetMapping(value = "/{id}")
    public Asset getById(@PathVariable long id){
        Optional<Asset> asset = repository.findById(id);

        if (!asset.isPresent())
            throw new CommentNotFoundException("id-" + id);

        return asset.get();
    }

    @PostMapping(value = "/createAsset")
    public ResponseEntity<?> saveAsset(@RequestBody Asset asset){
        Asset savedAsset = repository.save(asset);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedAsset.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Object> updateById(@RequestBody Asset asset, @PathVariable long id){

        Optional<Asset> studentOptional = repository.findById(id);

        if (!studentOptional.isPresent())
            return ResponseEntity.notFound().build();

        asset.setId(id);

        repository.save(asset);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/{id}")
    public void deleteAsset(@PathVariable long id){
        repository.deleteById(id);
    }
}
