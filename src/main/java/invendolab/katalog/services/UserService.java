package invendolab.katalog.services;

import invendolab.katalog.domain.User;

import java.util.List;

public interface UserService {
    List<User> listAll();

    User getById(Long id);

    User saveOrUpdate(User user);

    void delete(Long id);
}
