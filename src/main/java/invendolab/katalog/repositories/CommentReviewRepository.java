package invendolab.katalog.repositories;

import invendolab.katalog.models.CommentReview;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CommentReviewRepository  extends CrudRepository<CommentReview, Long>{
    List<CommentReview> findAll();
    List<CommentReview> findAllByProductId(long id);
}
