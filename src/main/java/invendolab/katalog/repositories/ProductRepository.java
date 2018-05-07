package invendolab.katalog.repositories;

import invendolab.katalog.models.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductRepository extends CrudRepository<Product, Long> {
    List<Product> findAll();
    List<Product> findAllByisActiveIsFalse();
}
