package mate.academy.internet.shop.dao;

import java.util.Optional;

import mate.academy.internet.shop.model.Bucket;

public interface BucketDao {

    Bucket create(Bucket bucket);

    Bucket update(Bucket bucket);

    Optional<Bucket> get(Long id);

    boolean delete(Bucket bucket);

    boolean deleteById(Long id);
}
