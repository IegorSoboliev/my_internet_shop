package mate.academy.internet.shop.dao;

import java.util.List;
import java.util.Optional;

import mate.academy.internet.shop.model.User;

public interface UserDao {

    User create(User user);

    User update(User user);

    Optional<User> get(Long id);

    List<User> getAll();

    boolean delete(User user);

    boolean deleteById(Long id);
}
