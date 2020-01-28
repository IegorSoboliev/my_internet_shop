package mate.academy.internet.shop.service.impl;

import java.util.List;
import java.util.NoSuchElementException;

import mate.academy.internet.shop.dao.UserDao;
import mate.academy.internet.shop.exceptions.AuthenticationException;
import mate.academy.internet.shop.exceptions.DataProcessingException;
import mate.academy.internet.shop.lib.Inject;
import mate.academy.internet.shop.lib.Service;
import mate.academy.internet.shop.model.User;
import mate.academy.internet.shop.service.UserService;

@Service
public class UserServiceImpl implements UserService {
    @Inject
    private static UserDao userDao;

    @Override
    public User create(User user) throws DataProcessingException {
        return userDao.create(user);
    }

    @Override
    public User update(User user) throws DataProcessingException {
        return userDao.update(user);
    }

    @Override
    public User get(Long id) throws DataProcessingException {
        return userDao.get(id)
                .orElseThrow(() -> new NoSuchElementException("Found no user with id " + id));
    }

    @Override
    public List<User> getAll() throws DataProcessingException {
        return userDao.getAll();
    }

    @Override
    public boolean deleteById(Long id) throws DataProcessingException {
        return userDao.deleteById(id);
    }

    @Override
    public User login(String email, String password) throws AuthenticationException,
            DataProcessingException {
        return userDao.login(email, password)
                .orElseThrow(() -> new AuthenticationException("Incorrect login or password"));
    }
}
