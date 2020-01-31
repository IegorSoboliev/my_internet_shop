package mate.academy.internet.shop.dao;

import java.util.List;
import java.util.Optional;

import mate.academy.internet.shop.exceptions.DataProcessingException;
import mate.academy.internet.shop.model.User;

public interface UserDao {

    User create(User user) throws DataProcessingException;

    User update(User user) throws DataProcessingException;

    Optional<User> get(Long id) throws DataProcessingException;

    List<User> getAll() throws DataProcessingException;

    boolean deleteById(Long id) throws DataProcessingException;

    Optional<User> findUserByEmail(String email) throws DataProcessingException;

    void setAdminRole(User user) throws DataProcessingException;
}
