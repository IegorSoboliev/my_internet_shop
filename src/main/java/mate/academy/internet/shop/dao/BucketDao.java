package mate.academy.internet.shop.dao;

import mate.academy.internet.shop.model.Bucket;
import mate.academy.internet.shop.model.Item;

import java.util.List;
import java.util.Optional;

public interface BucketDao {

    Bucket create(Bucket bucket);

    Bucket update(Bucket bucket);

    Optional<Bucket> get(Long id);

    boolean delete(Bucket bucket);

    boolean deleteById(Long id);
}
