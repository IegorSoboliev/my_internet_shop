package mate.academy.internet.shop.service;

import java.util.List;
import java.util.Optional;

import mate.academy.internet.shop.exceptions.AuthenticationException;
import mate.academy.internet.shop.model.User;

public interface UserService {

    User create(User user);

    User update(User user);

    User get(java.lang.Long id);

    Optional<User> findByToken(String token);

    List<User> getAll();

    boolean delete(User user);

    boolean deleteById(java.lang.Long id);

    User login(String email, String password) throws AuthenticationException;
}
