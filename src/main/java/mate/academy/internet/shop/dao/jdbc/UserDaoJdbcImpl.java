package mate.academy.internet.shop.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import mate.academy.internet.shop.dao.UserDao;
import mate.academy.internet.shop.exceptions.DataProcessingException;
import mate.academy.internet.shop.lib.Dao;
import mate.academy.internet.shop.model.Role;
import mate.academy.internet.shop.model.User;

@Dao
public class UserDaoJdbcImpl extends AbstractDao implements UserDao {
    private static final String USERS = "storage.users";
    private static final String USERS_ROLES = "storage.users_roles";
    private static final String ROLES = "storage.roles";
    private static final String BUCKETS = "storage.buckets";
    private static final String ORDERS = "storage.orders";

    public UserDaoJdbcImpl(Connection connection) {
        super(connection);
    }

    @Override
    public User create(User user) throws DataProcessingException {
        String addUser = String.format("INSERT INTO %s (name, surname, email, password, salt) "
                + "VALUES (?,?,?,?,?);", USERS);
        try (PreparedStatement statement
                     = connection.prepareStatement(addUser, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, user.getName());
            statement.setString(2, user.getSurname());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getPassword());
            statement.setBytes(5, user.getSalt());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                user.setId(resultSet.getLong(1));
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Cannot add user to database " + USERS
                    + " and return its id", e);
        }
        String addUserRole
                = String.format("INSERT INTO %s (user_id) VALUE (?);", USERS_ROLES);
        try (PreparedStatement statement
                     = connection.prepareStatement(addUserRole)) {
            statement.setLong(1, user.getId());
            statement.executeUpdate();
            return user;
        } catch (SQLException e) {
            throw new DataProcessingException("Cannot add userId to database " + USERS_ROLES, e);
        }
    }

    @Override
    public User update(User user) throws DataProcessingException {
        String updateUser = String.format("UPDATE %s SET name = ?, surname = ?, email = ?, "
                + "password = ?, salt = ? WHERE user_id = ?", USERS);
        try (PreparedStatement statement = connection.prepareStatement(updateUser)) {
            statement.setString(1, user.getName());
            statement.setString(2, user.getSurname());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getPassword());
            statement.setBytes(5, user.getSalt());
            statement.setLong(6, user.getId());
            statement.executeUpdate();
            return user;
        } catch (SQLException e) {
            throw new DataProcessingException("Cannot update user in database " + USERS, e);
        }
    }

    @Override
    public Optional<User> get(Long id) throws DataProcessingException {
        String getUser = String.format("SELECT * FROM %s WHERE user_id = ?;", USERS);
        try (PreparedStatement statement
                     = connection.prepareStatement(getUser)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return Optional.ofNullable(copyUserFromDB(id));
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Cannot show user from database " + USERS, e);
        }
        return Optional.empty();
    }

    @Override
    public List<User> getAll() throws DataProcessingException {
        List<User> allUsers = new ArrayList<>();
        String getAllUsers = String.format("SELECT * FROM %s;", USERS);
        try (PreparedStatement statement
                     = connection.prepareStatement(getAllUsers)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                User foundUser = copyUserFromDB(resultSet.getLong("user_id"));
                if (foundUser != null) {
                    allUsers.add(foundUser);
                }
            }
            return allUsers;
        } catch (SQLException e) {
            throw new DataProcessingException("Cannot show users from databases "
                    + USERS + USERS_ROLES + ROLES, e);
        }
    }

    @Override
    public boolean deleteById(Long id) throws DataProcessingException {
        String deleteUserBucket = String.format("DELETE FROM %S WHERE user_id = ?;", BUCKETS);
        String deleteUserOrders = String.format("DELETE FROM %s WHERE user_id = ?;", ORDERS);
        String deleteUserRoles = String.format("DELETE FROM %s WHERE user_id = ?;", USERS_ROLES);
        deleteUserData(deleteUserBucket, id);
        deleteUserData(deleteUserOrders, id);
        deleteUserData(deleteUserRoles, id);
        String deleteUser = String.format("DELETE FROM %S WHERE user_id = ?;", USERS);
        deleteUserData(deleteUser, id);
        return true;
    }

    @Override
    public Optional<User> findUserById(String email) throws DataProcessingException {
        String findUserByEmail = String.format("SELECT user_id FROM %s "
                + "WHERE email = ?;", USERS);
        try (PreparedStatement statement = connection.prepareStatement(findUserByEmail)) {
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Long userId = resultSet.getLong("user_id");
                return Optional.ofNullable(copyUserFromDB(userId));
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Cannot found user in database " + USERS, e);
        }
        return Optional.empty();
    }

    private User copyUserFromDB(Long userId) throws SQLException, DataProcessingException {
        User user = null;
        String getUser = String.format("SELECT * FROM %s INNER JOIN %s ON %s.user_id = %s.user_id "
                        + "INNER JOIN %s ON %s.role_id = %s.role_id "
                        + "WHERE %s.user_id = ?;",
                USERS, USERS_ROLES, USERS, USERS_ROLES, ROLES, USERS_ROLES, ROLES, USERS);
        try (PreparedStatement statement
                     = connection.prepareStatement(getUser)) {
            statement.setLong(1, userId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                if (user == null) {
                    user = new User();
                    String name = resultSet.getString("name");
                    String surname = resultSet.getString("surname");
                    String email = resultSet.getString("email");
                    String password = resultSet.getString("password");
                    byte[] salt = resultSet.getBytes("salt");
                    user.setId(userId);
                    user.setName(name);
                    user.setSurname(surname);
                    user.setEmail(email);
                    user.setPassword(password);
                    user.setSalt(salt);
                }
                String role = resultSet.getString("role_name");
                user.addRole(Role.of(role));
            }
            return user;
        } catch (SQLException e) {
            throw new DataProcessingException("Cannot found user in database " + USERS, e);
        }
    }

    private void deleteUserData(String query, Long userId) throws DataProcessingException {
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, userId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DataProcessingException("Cannot delete user from database", e);
        }
    }
}
