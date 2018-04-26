package invendolab.katalog.repositories;

import invendolab.katalog.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
