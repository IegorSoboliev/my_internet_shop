package mate.academy.internet.shop.dao;

import java.util.List;
import java.util.Optional;

import mate.academy.internet.shop.exceptions.DataProcessingException;
import mate.academy.internet.shop.model.Bucket;

public interface BucketDao {

    Bucket create(Bucket bucket) throws DataProcessingException;

    Bucket update(Bucket bucket) throws DataProcessingException;

    Optional<Bucket> get(Long id) throws DataProcessingException;

    boolean deleteById(Long id) throws DataProcessingException;

    List<Bucket> getAll() throws DataProcessingException;

    void removeBucketItemsFromDB(Bucket bucket) throws DataProcessingException;
}
