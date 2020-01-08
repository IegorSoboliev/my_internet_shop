package mate.academy.internet.shop.service;

import java.util.List;

import mate.academy.internet.shop.model.User;

public interface UserService {

    User create(User user);

    User update(User user);

    User get(java.lang.Long id);

    List<User> getAll();

    boolean delete(User user);

    boolean deleteById(java.lang.Long id);
}
