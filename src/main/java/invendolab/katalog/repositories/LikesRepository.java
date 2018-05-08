package invendolab.katalog.repositories;

import invendolab.katalog.models.Likes;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LikesRepository extends CrudRepository<Likes, Long> {
    List<Likes> findAll();
    List<Likes> findAllByProductId(long id);
}
