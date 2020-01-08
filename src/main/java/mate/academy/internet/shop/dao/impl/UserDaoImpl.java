package mate.academy.internet.shop.dao.impl;

import java.util.List;
import java.util.Optional;

import mate.academy.internet.shop.database.Storage;
import mate.academy.internet.shop.lib.Dao;
import mate.academy.internet.shop.model.User;

@Dao
public class UserDaoImpl implements mate.academy.internet.shop.dao.UserDao {
    private static Long userCount = 1L;

    @Override
    public User create(User user) {
        user.setId(userCount);
        Storage.users.add(user);
        userCount++;
        return user;
    }

    @Override
    public User update(User user) {
        for (User u : Storage.users) {
            if (u.getId().equals(user.getId())
                    && u.getEmail().equals(user.getEmail())) {
                u.setName(user.getName());
            }
        }
        return user;
    }

    @Override
    public Optional<User> get(Long id) {
        return Storage.users
                .stream()
                .filter(user -> user.getId().equals(id))
                .findFirst();
    }

    @Override
    public List<User> getAll() {
        return Storage.users;
    }

    @Override
    public boolean delete(User user) {
        return Storage.users.remove(user);
    }

    @Override
    public boolean deleteById(Long id) {
        for (User u : Storage.users) {
            if (u.getId().equals(id)) {
                Storage.users.remove(u);
                return true;
            }
        }
        return false;
    }
}
