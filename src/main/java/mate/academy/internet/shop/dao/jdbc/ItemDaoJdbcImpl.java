package mate.academy.internet.shop.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import mate.academy.internet.shop.dao.ItemDao;
import mate.academy.internet.shop.exceptions.DataProcessingException;
import mate.academy.internet.shop.lib.Dao;
import mate.academy.internet.shop.model.Item;

@Dao
public class ItemDaoJdbcImpl extends AbstractDao<Item> implements ItemDao {
    private static final String ITEMS = "storage.items";
    private static final String ORDERS_ITEMS = "storage.orders_items";
    private static final String BUCKETS_ITEMS = "storage.buckets_items";

    public ItemDaoJdbcImpl(Connection connection) {
        super(connection);
    }

    @Override
    public Item create(Item item) throws DataProcessingException {
        String createItem = String.format("INSERT INTO %s (item_name, price) VALUES (?, ?);",
                ITEMS);
        try (PreparedStatement statement = connection.prepareStatement(createItem,
                Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, item.getName());
            statement.setInt(2, item.getPrice());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            while (resultSet.next()) {
                item.setId(resultSet.getLong(1));
            }
            return item;
        } catch (SQLException e) {
            throw new DataProcessingException("Cannot add item to database " + ITEMS, e);
        }
    }

    @Override
    public Item update(Item item) throws DataProcessingException {
        String updateItem = String.format("UPDATE %s SET item_name = ?, price = ? "
                + "WHERE item_id = ?;", ITEMS);
        try (PreparedStatement statement = connection.prepareStatement(updateItem)) {
            statement.setString(1, item.getName());
            statement.setInt(2, item.getPrice());
            statement.setLong(3, item.getId());
            statement.executeUpdate();
            return item;
        } catch (SQLException e) {
            throw new DataProcessingException("Cannot update item in database " + ITEMS, e);
        }
    }

    @Override
    public Optional<Item> get(Long id) throws DataProcessingException {
        String getItem = String.format("SELECT * FROM %s WHERE item_id = ?", ITEMS);
        try (PreparedStatement statement = connection.prepareStatement(getItem)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                return Optional.of(copyItemFromDB(resultSet));
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Cannot show item from database " + ITEMS, e);
        }
        return Optional.empty();
    }

    @Override
    public List<Item> getAll() throws DataProcessingException {
        List<Item> allItems = new ArrayList<>();
        String getItems = String.format("SELECT * FROM %s;", ITEMS);
        try (PreparedStatement statement = connection.prepareStatement(getItems)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Item found = copyItemFromDB(resultSet);
                allItems.add(found);
            }
            return allItems;
        } catch (SQLException e) {
            throw new DataProcessingException("Cannot show items from database " + ITEMS, e);
        }
    }

    @Override
    public boolean deleteById(Long id) throws DataProcessingException {
        String deleteItemIdFromOrders = String.format("DELETE FROM %s WHERE item_id = ?;",
                ORDERS_ITEMS);
        String deleteItemFromBuckets = String.format("DELETE FROM %s WHERE item_id = ?;",
                BUCKETS_ITEMS);
        String deleteItem = String.format("DELETE FROM %s WHERE item_id = ?;", ITEMS);
        deleteItemData(deleteItemFromBuckets, id);
        deleteItemData(deleteItemIdFromOrders, id);
        deleteItemData(deleteItem, id);
        return true;
    }

    private Item copyItemFromDB(ResultSet resultSet) throws SQLException {
        Long itemId = resultSet.getLong("item_id");
        String name = resultSet.getString("item_name");
        Integer price = resultSet.getInt("price");
        Item item = new Item();
        item.setId(itemId);
        item.setName(name);
        item.setPrice(price);
        return item;
    }

    private void deleteItemData(String query, Long itemId) throws DataProcessingException {
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, itemId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DataProcessingException("Cannot delete item from database", e);
        }
    }
}
