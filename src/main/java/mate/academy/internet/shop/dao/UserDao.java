package mate.academy.internet.shop.dao;

import java.util.List;
import java.util.Optional;

import mate.academy.internet.shop.model.User;

public interface UserDao {

    User create(User user);

    User update(User user);

    Optional<User> get(java.lang.Long id);

    Optional<User> getByToken(String token);

    List<User> getAll();

    boolean delete(User user);

    boolean deleteById(java.lang.Long id);

    Optional<User> findByLogin(String email);
}
