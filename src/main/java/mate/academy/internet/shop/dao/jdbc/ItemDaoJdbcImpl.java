package mate.academy.internet.shop.dao.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import mate.academy.internet.shop.dao.ItemDao;
import mate.academy.internet.shop.lib.Dao;
import mate.academy.internet.shop.model.Item;
import org.apache.log4j.Logger;

@Dao
public class ItemDaoJdbcImpl extends AbstractDao<Item> implements ItemDao {
    private static final Logger LOGGER = Logger.getLogger(ItemDaoJdbcImpl.class);
    private static final String DB_TABLE = "storage.items";

    public ItemDaoJdbcImpl(Connection connection) {
        super(connection);
    }

    @Override
    public Item create(Item item) {
        String query = String.format("INSERT INTO %s (name,price) VALUES ('%s',%d);",
                DB_TABLE, item.getName(), item.getPrice());
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(query);
        } catch (SQLException e) {
            LOGGER.warn("Cannot add item to database", e);
        }
        return item;
    }

    @Override
    public Item update(Item item) {
        String query = String.format("UPDATE %s SET name = %s, price = %d WHERE id = %d;",
                DB_TABLE, item.getName(), item.getPrice(), item.getId());
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(query);
        } catch (SQLException e) {
            LOGGER.warn("Cannot update item in database", e);
        }
        return item;
    }

    @Override
    public Optional<Item> get(Long id) {
        String query = String.format("SELECT * FROM %s WHERE id = %d;", DB_TABLE, id);
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                Item needed = copyItemFromDbTable(resultSet);
                return Optional.of(needed);
            }
        } catch (SQLException e) {
            LOGGER.warn("Cannot show item from database");
        }
        return Optional.empty();
    }

    @Override
    public List<Item> getAll() {
        List<Item> allItems = new ArrayList<>();
        String query = String.format("SELECT * FROM %s;", DB_TABLE);
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                Item toAdd = copyItemFromDbTable(resultSet);
                allItems.add(toAdd);
            }
        } catch (SQLException e) {
            LOGGER.warn("Cannot show items from database", e);
        }
        return allItems;
    }

    @Override
    public boolean deleteById(Long id) {
        String query = String.format("DELETE FROM %s WHERE id = %d;", DB_TABLE, id);
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(query);
            return true;
        } catch (SQLException e) {
            LOGGER.warn("Cannot delete item from database", e);
        }
        return false;
    }

    private Item copyItemFromDbTable(ResultSet resultSet) throws SQLException {
        Long itemId = resultSet.getLong("id");
        String name = resultSet.getString("name");
        Integer price = resultSet.getInt("price");
        Item item = new Item();
        item.setId(itemId);
        item.setName(name);
        item.setPrice(price);
        return item;
    }
}