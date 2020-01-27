package mate.academy.internet.shop.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import mate.academy.internet.shop.dao.OrderDao;
import mate.academy.internet.shop.exceptions.DataProcessingException;
import mate.academy.internet.shop.lib.Dao;
import mate.academy.internet.shop.model.Item;
import mate.academy.internet.shop.model.Order;

@Dao
public class OrderDaoJdbcImpl extends AbstractDao<Order> implements OrderDao {
    private static final String ORDERS = "storage.orders";
    private static final String ORDERS_ITEMS = "storage.orders_items";
    private static final String ITEMS = "storage.items";

    public OrderDaoJdbcImpl(Connection connection) {
        super(connection);
    }

    @Override
    public Order create(Order order) throws DataProcessingException {
        String addOrder = (String.format("INSERT INTO %s (user_id) VALUE (?);", ORDERS));
        try (PreparedStatement statement
                     = connection.prepareStatement(addOrder, Statement.RETURN_GENERATED_KEYS)) {
            statement.setLong(1, order.getUserId());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            while (resultSet.next()) {
                order.setId(resultSet.getLong(1));
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Cannot add order to database " + ORDERS
                    + " and return its id", e);
        }
        String addOrderData = String.format("INSERT INTO "
                + "%s (order_id, item_id) VALUES (?, ?);", ORDERS_ITEMS);
        for (Item item : order.getItems()) {
            try (PreparedStatement statement
                         = connection.prepareStatement(addOrderData)) {
                statement.setLong(1, order.getId());
                statement.setLong(2, item.getId());
                statement.executeUpdate();
            } catch (SQLException e) {
                throw new DataProcessingException("Cannot add order id and items to"
                        + ORDERS_ITEMS, e);
            }
        }
        return order;
    }

    @Override
    public Order update(Order order) throws DataProcessingException {
        removeOrderItemsFromDB(order);
        addOrderItems(order);
        return order;
    }

    @Override
    public Optional<Order> get(Long id) throws DataProcessingException {
        Order toFind = new Order();
        String getOrder = String.format("SELECT * FROM %s INNER JOIN %s ON %s.order_id = "
                        + "%s.order_id INNER JOIN %s ON %s.item_id = %s.item_id WHERE %s.order_id "
                        + "= ?;", ORDERS, ORDERS_ITEMS, ORDERS, ORDERS_ITEMS, ITEMS,
                ORDERS_ITEMS, ITEMS, ORDERS);
        try (PreparedStatement statement
                     = connection.prepareStatement(getOrder)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                toFind.setId(resultSet.getLong("order_id"));
                toFind.setId(resultSet.getLong("user_id"));
                toFind.setItems(copyOrderItems(toFind));
                return Optional.of(toFind);
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Cannot show user from database " + ORDERS, e);
        }
        return Optional.empty();
    }

    @Override
    public List<Order> getUserOrders(Long userId) throws DataProcessingException {
        List<Order> userOrders = new ArrayList<>();
        String getUserOrders = String.format("SELECT * FROM %s INNER JOIN %s ON %s.order_id = "
                        + "%s.order_id INNER JOIN %s ON %s.item_id = %s.item_id WHERE "
                        + "%s.user_id = ?;", ORDERS, ORDERS_ITEMS, ORDERS, ORDERS_ITEMS, ITEMS,
                ORDERS_ITEMS, ITEMS, ORDERS);
        try (PreparedStatement statement = connection.prepareStatement(getUserOrders);) {
            statement.setLong(1, userId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Order found = new Order();
                found.setId(resultSet.getLong("order_id"));
                found.setItems(copyOrderItems(found));
                userOrders.add(found);
            }
            return userOrders;
        } catch (SQLException e) {
            throw new DataProcessingException("Cannot show orders and items from databases "
                    + ORDERS_ITEMS + ITEMS, e);
        }
    }

    @Override
    public List<Order> getAll() throws DataProcessingException {
        List<Order> allOrders = new ArrayList<>();
        String getUserOrders = String.format("SELECT * FROM %s INNER JOIN %s ON %s.order_id = "
                        + "%s.order_id INNER JOIN %s ON %s.item_id = %s.item_id;", ORDERS,
                ORDERS_ITEMS, ORDERS, ORDERS_ITEMS, ITEMS, ORDERS_ITEMS, ITEMS);
        try (PreparedStatement statement = connection.prepareStatement(getUserOrders);) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Order found = new Order();
                found.setId(resultSet.getLong("order_id"));
                found.setUserId(resultSet.getLong("user_id"));
                found.setItems(copyOrderItems(found));
                allOrders.add(found);
            }
            return allOrders;
        } catch (SQLException e) {
            throw new DataProcessingException("Cannot show orders and items from databases "
                    + ORDERS_ITEMS + ITEMS, e);
        }
    }

    @Override
    public boolean deleteById(Long id) throws DataProcessingException {
        String deleteOrderData = String.format("DELETE FROM %s WHERE order_id = ?;", ORDERS_ITEMS);
        try (PreparedStatement statement = connection.prepareStatement(deleteOrderData)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DataProcessingException("Cannot delete order from database "
                    + ORDERS_ITEMS, e);
        }
        String deleteOrder = String.format("DELETE FROM %S WHERE order_id = ?;", ORDERS);
        try (PreparedStatement statement = connection.prepareStatement(deleteOrder)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DataProcessingException("Cannot delete order from database " + ORDERS, e);
        }
        return true;
    }

    private void addOrderItems(Order order) throws DataProcessingException {
        String addOrderData = String.format("INSERT INTO "
                + "%s (order_id, item_id) VALUES (?, ?);", ORDERS_ITEMS);
        try (PreparedStatement statement
                     = connection.prepareStatement(addOrderData)) {
            statement.setLong(1, order.getId());
            for (Item item : order.getItems()) {
                statement.setLong(2, item.getId());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Cannot add order id and items to database "
                    + ORDERS_ITEMS, e);
        }
    }

    public void removeOrderItemsFromDB(Order order) throws DataProcessingException {
        String deleteOrderItems = String.format("DELETE FROM %s WHERE order_id = ?;",
                ORDERS_ITEMS);
        try (PreparedStatement statement = connection.prepareStatement(deleteOrderItems)) {
            statement.setLong(1, order.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DataProcessingException("Cannot delete order items from database "
                    + ORDERS_ITEMS, e);
        }
    }

    private List<Item> copyOrderItems(Order order) throws DataProcessingException {
        List<Item> orderItems = new ArrayList<>();
        String getOrderItems = String.format("SELECT * FROM %s INNER JOIN %s ON %s.item_id "
                        + "= %s.item_id WHERE %s.order_id = ?;", ORDERS_ITEMS, ITEMS,
                ORDERS_ITEMS, ITEMS, ORDERS_ITEMS);
        try (PreparedStatement statement = connection.prepareStatement(getOrderItems)) {
            statement.setLong(1, order.getId());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Long itemId = resultSet.getLong("item_id");
                String name = resultSet.getString("item_name");
                Integer price = resultSet.getInt("price");
                Item found = new Item();
                found.setId(itemId);
                found.setName(name);
                found.setPrice(price);
                orderItems.add(found);
            }
            return orderItems;
        } catch (SQLException e) {
            throw new DataProcessingException("Cannot get order items from database "
                    + ORDERS_ITEMS, e);
        }
    }
}
