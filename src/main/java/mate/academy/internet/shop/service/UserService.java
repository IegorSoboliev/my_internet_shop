package mate.academy.internet.shop.service;

import mate.academy.internet.shop.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    User create(User user);

    User update(User user);

    User get(Long id);

    List<User> getAll();

    boolean delete(User user);

    boolean deleteById(Long id);
}
