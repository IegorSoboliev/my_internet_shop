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
import mate.academy.internet.shop.exceptions.DataProcessingException;
import mate.academy.internet.shop.lib.Dao;
import mate.academy.internet.shop.model.Bucket;
import mate.academy.internet.shop.model.Item;

@Dao
public class BucketDaoJdbcImpl extends AbstractDao implements BucketDao {
    private static final String BUCKETS = "storage.buckets";
    private static final String BUCKETS_ITEMS = "storage.buckets_items";
    private static final String ITEMS = "storage.items";

    public BucketDaoJdbcImpl(Connection connection) {
        super(connection);
    }

    @Override
    public Bucket create(Bucket bucket) throws DataProcessingException {
        String addBucket = (String.format("INSERT INTO %s (user_id) VALUE (?);", BUCKETS));
        try (PreparedStatement statement
                     = connection.prepareStatement(addBucket, Statement.RETURN_GENERATED_KEYS)) {
            statement.setLong(1, bucket.getUserId());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();
            bucket.setId(resultSet.getLong(1));
            return addBucketItems(bucket);
        } catch (SQLException e) {
            throw new DataProcessingException("Cannot add bucket to database " + BUCKETS + " and "
                    + "return its id", e);
        }
    }

    @Override
    public Bucket update(Bucket bucket) throws DataProcessingException {
        removeBucketItemsFromDB(bucket);
        addBucketItems(bucket);
        return bucket;
    }

    @Override
    public Optional<Bucket> get(Long id) throws DataProcessingException {
        String getBucket = String.format("SELECT * FROM %s WHERE bucket_id = ?;", BUCKETS);
        Optional<Bucket> toFind = getBucketFromDB(getBucket, id);
        return toFind;
    }

    @Override
    public Optional<Bucket> getByUserId(Long userId) throws DataProcessingException {
        String getBucketByUserId = String.format("SELECT * FROM %s WHERE user_id = ?;", BUCKETS);
        Optional<Bucket> toFind = getBucketFromDB(getBucketByUserId, userId);
        return toFind;
    }

    @Override
    public List<Bucket> getAll() throws DataProcessingException {
        List<Bucket> allBuckets = new ArrayList<>();
        String getallBuckets = String.format("SELECT * FROM %s;", BUCKETS);
        try (PreparedStatement statement = connection.prepareStatement(getallBuckets);) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Bucket found = new Bucket();
                found.setId(resultSet.getLong("bucket_id"));
                found.setUserId(resultSet.getLong("user_id"));
                found.setItems(copyBucketItems(found));
                allBuckets.add(found);
            }
            return allBuckets;
        } catch (SQLException e) {
            throw new DataProcessingException("Cannot show buckets and items from databases "
                    + BUCKETS_ITEMS + ITEMS, e);
        }
    }

    @Override
    public boolean deleteById(Long id) throws DataProcessingException {
        String deleteBucket = String.format("DELETE FROM %S WHERE bucket_id = ?;", BUCKETS);
        try (PreparedStatement statement = connection.prepareStatement(deleteBucket)) {
            statement.setLong(1, id);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new DataProcessingException("Cannot delete bucket from database " + BUCKETS, e);
        }
    }

    @Override
    public void removeBucketItemsFromDB(Bucket bucket) throws DataProcessingException {
        String deleteBucketItems = String.format("DELETE FROM %s WHERE bucket_id = ?;",
                BUCKETS_ITEMS);
        try (PreparedStatement statement = connection.prepareStatement(deleteBucketItems)) {
            statement.setLong(1, bucket.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DataProcessingException("Cannot delete bucket data from database "
                    + BUCKETS_ITEMS, e);
        }
    }

    private Bucket addBucketItems(Bucket bucket) throws DataProcessingException {
        String addBucketData = String.format("INSERT INTO "
                + "%s (bucket_id, item_id) VALUES (?, ?);", BUCKETS_ITEMS);
        try (PreparedStatement statement
                     = connection.prepareStatement(addBucketData)) {
            for (Item item : bucket.getItems()) {
                statement.setLong(1, bucket.getId());
                statement.setLong(2, item.getId());
                statement.executeUpdate();
            }
            return bucket;
        } catch (SQLException e) {
            throw new DataProcessingException("Cannot add bucket id and its items to"
                    + BUCKETS_ITEMS, e);
        }
    }

    private List<Item> copyBucketItems(Bucket bucket) throws DataProcessingException {
        List<Item> bucketItems = new ArrayList<>();
        String getBucketItems = String.format("SELECT * FROM %s INNER JOIN %s ON %s.item_id "
                        + "= %s.item_id WHERE %s.bucket_id = ?;", BUCKETS_ITEMS, ITEMS,
                BUCKETS_ITEMS, ITEMS, BUCKETS_ITEMS);
        try (PreparedStatement statement = connection.prepareStatement(getBucketItems)) {
            statement.setLong(1, bucket.getId());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Long itemId = resultSet.getLong("item_id");
                String name = resultSet.getString("item_name");
                Integer price = resultSet.getInt("price");
                Item found = new Item();
                found.setId(itemId);
                found.setName(name);
                found.setPrice(price);
                bucketItems.add(found);
            }
            return bucketItems;
        } catch (SQLException e) {
            throw new DataProcessingException("Cannot show items from database " + ITEMS, e);
        }
    }

    private Optional<Bucket> getBucketFromDB(String query, Long id) throws DataProcessingException {
        try (PreparedStatement statement
                     = connection.prepareStatement(query)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Bucket found = new Bucket();
                found.setId(resultSet.getLong("bucket_id"));
                found.setUserId(resultSet.getLong("user_id"));
                found.setItems(copyBucketItems(found));
                return Optional.of(found);
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Cannot show bucket and its items from databases "
                    + BUCKETS + BUCKETS_ITEMS + ITEMS, e);
        }
        return Optional.empty();
    }
}
