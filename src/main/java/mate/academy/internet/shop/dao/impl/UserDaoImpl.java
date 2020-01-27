package mate.academy.internet.shop.dao.impl;

import java.util.List;
import java.util.Optional;

import mate.academy.internet.shop.database.Storage;
import mate.academy.internet.shop.lib.Dao;
import mate.academy.internet.shop.model.User;

@Dao
public class UserDaoImpl {

    public User add(User user) {
        Storage.users.add(user);
        return user;
    }

    public User update(User user) {
        Storage.users
                .stream()
                .filter(u -> u.getId().equals(u.getId()))
                .findFirst()
                .ifPresent(u -> u.setName(u.getName()));
        return user;
    }

    public Optional<User> get(java.lang.Long id) {
        return Storage.users
                .stream()
                .filter(user -> user.getId().equals(id))
                .findFirst();
    }

    public List<User> getAll() {
        return Storage.users;
    }

    public boolean deleteById(Long id) {
        return Storage.users.removeIf(u -> u.getId().equals(id));
    }

    public Optional<User> login(String email, String password) {
        return Storage.users
                .stream()
                .filter(u -> u.getEmail().equals(email) && u.getPassword().equals(password))
                .findFirst();
    }
}
