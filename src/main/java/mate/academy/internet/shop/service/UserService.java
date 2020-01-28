package mate.academy.internet.shop.service;

import java.util.List;

import mate.academy.internet.shop.exceptions.AuthenticationException;
import mate.academy.internet.shop.exceptions.DataProcessingException;
import mate.academy.internet.shop.model.User;

public interface UserService {

    User create(User user) throws DataProcessingException;

    User update(User user) throws DataProcessingException;

    User get(Long id) throws DataProcessingException;

    List<User> getAll() throws DataProcessingException;

    boolean deleteById(Long id) throws DataProcessingException;

    User login(String email, String password) throws AuthenticationException,
            DataProcessingException;
}
