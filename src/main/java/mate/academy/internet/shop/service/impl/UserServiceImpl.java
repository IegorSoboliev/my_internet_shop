package mate.academy.internet.shop.service.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

import mate.academy.internet.shop.dao.UserDao;
import mate.academy.internet.shop.exceptions.AuthenticationException;
import mate.academy.internet.shop.lib.Inject;
import mate.academy.internet.shop.lib.Service;
import mate.academy.internet.shop.model.User;
import mate.academy.internet.shop.service.UserService;

@Service
public class UserServiceImpl implements UserService {
    @Inject
    private static UserDao userDao;

    @Override
    public User create(User user) {
        user.setToken(generateToken());
        return userDao.create(user);
    }

    private String generateToken() {
        return UUID.randomUUID().toString();
    }

    @Override
    public User update(User user) {
        return userDao.update(user);
    }

    @Override
    public User get(Long id) {
        return userDao.get(id)
                .orElseThrow(() -> new NoSuchElementException("Found no user with id " + id));
    }

    public Optional<User> findByToken(String token) {
        return userDao.getByToken(token);
    }

    @Override
    public List<User> getAll() {
        return userDao.getAll();
    }

    @Override
    public boolean delete(User user) {
        return userDao.delete(user);
    }

    @Override
    public boolean deleteById(Long id) {
        return userDao.deleteById(id);
    }

    @Override
    public User login(String email, String password) throws AuthenticationException {
        Optional<User> user = userDao.findByLogin(email);
        if (user.isEmpty() || !user.get().getPassword().equals(password)) {
            throw new AuthenticationException("Incorrect login or password");
        }
        return user.get();
    }
}

