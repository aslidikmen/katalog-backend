package invendolab.katalog.repositories;

import invendolab.katalog.models.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductRepository extends CrudRepository<Product, Long> {
    List<Product> findAll();
    List<Product> findAllByisActiveIsFalse();
    List<Product> findAllByisActiveIsTrue();

    List<Product> findAllByisActiveIsTrueOrderByIdDesc();
    List<Product> findAllByisActiveIsTrueOrderByTitleDesc();
    List<Product> findAllByisActiveIsTrueOrderByUpVoteCountDesc();
    List<Product> findAllByisActiveIsTrueOrderByDownVoteCountDesc();
    List<Product> findAllByisActiveIsTrueOrderByPriceDesc();

    List<Product> findAllByisActiveIsTrueOrderByIdAsc();
    List<Product> findAllByisActiveIsTrueOrderByTitleAsc();
    List<Product> findAllByisActiveIsTrueOrderByUpVoteCountAsc();
    List<Product> findAllByisActiveIsTrueOrderByDownVoteCountAsc();
    List<Product> findAllByisActiveIsTrueOrderByPriceAsc();

}
