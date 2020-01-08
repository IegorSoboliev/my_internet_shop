package mate.academy.internet.shop.dao.impl;

import java.util.List;
import java.util.Optional;

import mate.academy.internet.shop.dao.UserDao;
import mate.academy.internet.shop.database.Storage;
import mate.academy.internet.shop.lib.Dao;
import mate.academy.internet.shop.model.User;

@Dao
public class UserDaoImpl implements UserDao {
    private static java.lang.Long idGenerator = 1L;

    @Override
    public User create(User user) {
        user.setId(idGenerator);
        Storage.users.add(user);
        idGenerator++;
        return user;
    }

    @Override
    public User update(User user) {
        Storage.users
                .stream()
                .filter(u -> u.getId().equals(u.getId()))
                .findFirst()
                .ifPresent(u -> u.setName(u.getName()));
        return user;
    }

    @Override
    public Optional<User> get(java.lang.Long id) {
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
    public boolean deleteById(java.lang.Long id) {
        return Storage.users.removeIf(u -> u.getId().equals(id));
    }
}
