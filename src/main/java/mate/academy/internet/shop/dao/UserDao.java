package mate.academy.internet.shop.dao;

import mate.academy.internet.shop.model.Order;
import mate.academy.internet.shop.model.User;

import java.util.List;
import java.util.Optional;

public interface UserDao {

    User create(User user);

    User update(User user);

    Optional<User> get(Long id);

    List<User> getAll();

    boolean delete(User user);

    boolean deleteById(Long id);
}
