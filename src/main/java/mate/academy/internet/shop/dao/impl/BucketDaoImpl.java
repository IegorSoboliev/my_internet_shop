package mate.academy.internet.shop.dao.impl;

import java.util.List;
import java.util.Optional;

import mate.academy.internet.shop.dao.BucketDao;
import mate.academy.internet.shop.database.Storage;
import mate.academy.internet.shop.lib.Dao;
import mate.academy.internet.shop.model.Bucket;

@Dao
public class BucketDaoImpl implements BucketDao {

    @Override
    public Bucket create(Bucket bucket) {
        Storage.buckets.add(bucket);
        return bucket;
    }

    @Override
    public Bucket update(Bucket bucket) {
        Storage.buckets
                .stream()
                .filter(b -> b.getId().equals(bucket.getId()))
                .findFirst()
                .ifPresent(b -> b.setItems(bucket.getItems()));
        return bucket;
    }

    @Override
    public Optional<Bucket> get(Long id) {
        return Storage.buckets
                .stream()
                .filter(bucket -> bucket.getId().equals(id))
                .findFirst();
    }

    @Override
    public boolean delete(Bucket bucket) {
        return Storage.buckets.remove(bucket);
    }

    @Override
    public boolean deleteById(Long id) {
        return Storage.buckets.removeIf(b -> b.getId().equals(id));
    }

    @Override
    public List<Bucket> getAll() {
        return Storage.buckets;
    }
}
