package invendolab.katalog.repositories;

import invendolab.katalog.domain.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {
}
