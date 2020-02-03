package mate.academy.internet.shop.service;

import java.sql.SQLException;
import java.util.List;

import mate.academy.internet.shop.exceptions.DataProcessingException;
import mate.academy.internet.shop.model.Bucket;
import mate.academy.internet.shop.model.Item;

public interface BucketService {

    Bucket create(Bucket bucket) throws DataProcessingException;

    Bucket update(Bucket bucket) throws DataProcessingException;

    Bucket get(Long id) throws DataProcessingException;

    Bucket getByUserId(Long userId) throws DataProcessingException;

    boolean deleteById(Long id) throws DataProcessingException;

    void addItem(Bucket bucket, Item item) throws DataProcessingException;

    void deleteItem(Bucket bucket, Item item) throws DataProcessingException;

    void clear(Bucket bucket) throws DataProcessingException;

    List<Bucket> getAll() throws SQLException, DataProcessingException;
}
