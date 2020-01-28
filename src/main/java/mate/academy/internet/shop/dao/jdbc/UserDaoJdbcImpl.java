package mate.academy.internet.shop.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import mate.academy.internet.shop.dao.BucketDao;
import mate.academy.internet.shop.dao.OrderDao;
import mate.academy.internet.shop.dao.UserDao;
import mate.academy.internet.shop.exceptions.DataProcessingException;
import mate.academy.internet.shop.lib.Dao;
import mate.academy.internet.shop.lib.Inject;
import mate.academy.internet.shop.model.Role;
import mate.academy.internet.shop.model.User;

@Dao
public class UserDaoJdbcImpl extends AbstractDao implements UserDao {
    private static final String USERS = "storage.users";
    private static final String USERS_ROLES = "storage.users_roles";
    private static final String ROLES = "storage.roles";
    private static final String BUCKETS = "storage.buckets";
    private static final String ORDERS = "storage.orders";

    @Inject
    private static BucketDao bucketDao;
    private static OrderDao orderDao;

    public UserDaoJdbcImpl(Connection connection) {
        super(connection);
    }

    @Override
    public User create(User user) throws DataProcessingException {
        String addUser = String.format("INSERT INTO %s (name, surname, email, password) "
                + "VALUES (?,?,?,?);", USERS);
        try (PreparedStatement statement
                     = connection.prepareStatement(addUser, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, user.getName());
            statement.setString(2, user.getSurname());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getPassword());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next(); {
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
                + "password = ? WHERE user_id = ?", USERS);
        try (PreparedStatement statement = connection.prepareStatement(updateUser)) {
            statement.setString(1, user.getName());
            statement.setString(2, user.getSurname());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getPassword());
            statement.setLong(5, user.getId());
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
//            while (resultSet.next())
            resultSet.next();
            {
                return Optional.of(copyUserFromDB(id));
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Cannot show user from database " + USERS, e);
        }
    }

    @Override
    public List<User> getAll() throws DataProcessingException {
        List<User> allUsers = new ArrayList<>();
        String getAllUsers = String.format("SELECT * FROM %s;", USERS);
        try (PreparedStatement statement
                     = connection.prepareStatement(getAllUsers)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                User found = new User();
                found.setId(resultSet.getLong("user_id"));
                found = copyUserFromDB(found.getId());
                allUsers.add(found);
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
    public Optional<User> login(String email, String password) throws DataProcessingException {
        Long userId = null;
        String verifyUser = String.format("SELECT user_id FROM %s "
                + "WHERE email = ? AND password = ?;", USERS);
        try (PreparedStatement statement = connection.prepareStatement(verifyUser)) {
            statement.setString(1, email);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            {
                userId = resultSet.getLong("user_id");
            }
            return Optional.of(copyUserFromDB(userId));
        } catch (SQLException e) {
            throw new DataProcessingException("Cannot found user in database " + USERS, e);
        }
    }

    private User copyUserFromDB(Long userId) throws SQLException, DataProcessingException {
        User found = new User();
        String getUser = String.format("SELECT * FROM %s INNER JOIN %s ON %s.user_id = %s.user_id "
                        + "INNER JOIN %s ON %s.role_id = %s.role_id "
                        + "WHERE %s.user_id = ?;",
                USERS, USERS_ROLES, USERS, USERS_ROLES, ROLES, USERS_ROLES, ROLES, USERS);
        try (PreparedStatement statement
                     = connection.prepareStatement(getUser)) {
            statement.setLong(1, userId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");
                String role = resultSet.getString("role_name");
                found.setId(userId);
                found.setName(name);
                found.setSurname(surname);
                found.setEmail(email);
                found.setPassword(password);
                found.addRole(Role.of(role));
            }
            return found;
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

