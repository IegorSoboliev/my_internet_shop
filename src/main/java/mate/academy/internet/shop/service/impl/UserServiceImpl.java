package mate.academy.internet.shop.service.impl;

import static mate.academy.internet.shop.util.HashUtil.getSalt;
import static mate.academy.internet.shop.util.HashUtil.hashPassword;

import java.util.List;
import java.util.Optional;

import mate.academy.internet.shop.dao.UserDao;
import mate.academy.internet.shop.exceptions.AuthenticationException;
import mate.academy.internet.shop.exceptions.DataProcessingException;
import mate.academy.internet.shop.exceptions.EmailAlreadyRegisteredException;
import mate.academy.internet.shop.lib.Inject;
import mate.academy.internet.shop.lib.Service;
import mate.academy.internet.shop.model.User;
import mate.academy.internet.shop.service.UserService;

@Service
public class UserServiceImpl implements UserService {
    @Inject
    private static UserDao userDao;

    @Override
    public User create(User user) throws DataProcessingException, EmailAlreadyRegisteredException {
        if (userDao.findUserByEmail(user.getEmail()).isPresent()) {
            throw new EmailAlreadyRegisteredException("This email already registered");
        }
        byte[] salt = getSalt();
        String enteredPassword = user.getPassword();
        String hashedPassword = hashPassword(enteredPassword, salt);
        user.setSalt(salt);
        user.setPassword(hashedPassword);
        return userDao.create(user);
    }

    @Override
    public User update(User user) throws DataProcessingException {
        return userDao.update(user);
    }

    @Override
    public User get(Long id) throws DataProcessingException {
        return userDao.get(id)
                .orElseThrow(() -> new DataProcessingException("Found no user with id " + id));
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
    public User login(String email, String enteredPassword) throws AuthenticationException,
            DataProcessingException {
        Optional<User> user = userDao.findUserByEmail(email);
        if (user.isEmpty()
                || !hashPassword(enteredPassword, user.get().getSalt())
                .equals(user.get().getPassword())) {
            throw new AuthenticationException("Incorrect login or password");
        }
        return user.get();
    }

    @Override
    public void setAdminRole(User user) throws DataProcessingException {
        userDao.setAdminRole(user);
    }
}
