package invendolab.katalog.repositories;

import invendolab.katalog.models.Consumer;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ConsumerRepository extends CrudRepository<Consumer, Long> {
    List<Consumer> findAllByOrderByIdDesc();
    List<Consumer> findAll();
}
