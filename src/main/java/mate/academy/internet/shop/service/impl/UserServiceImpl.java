package mate.academy.internet.shop.service.impl;

import mate.academy.internet.shop.dao.UserDao;
import mate.academy.internet.shop.lib.Inject;
import mate.academy.internet.shop.lib.Service;
import mate.academy.internet.shop.model.Order;
import mate.academy.internet.shop.model.User;
import mate.academy.internet.shop.service.UserService;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UserServiceImpl implements UserService {
    @Inject
    private static UserDao userDao;

    @Override
    public User create(User user) {
        return userDao.create(user);
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
}
