package invendolab.katalog.repositories;

import invendolab.katalog.models.Product;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SearchRepository extends CrudRepository<Product, Long> {

    List<Product> findAll(Specification<Product> spec, PageRequest page);
    List<Product> findAll(Specification<Product> spec);

}
