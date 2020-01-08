package mate.academy.internet.shop.dao.impl;

import mate.academy.internet.shop.dao.BucketDao;
import mate.academy.internet.shop.database.Storage;
import mate.academy.internet.shop.lib.Dao;
import mate.academy.internet.shop.model.Bucket;
import mate.academy.internet.shop.model.Item;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Dao
public class BucketDaoImpl implements BucketDao {
    private static Long bucketCounter = 1l;

    @Override
    public Bucket create(Bucket bucket) {
        bucket.setId(bucketCounter);
        Storage.buckets.add(bucket);
        bucketCounter++;
        return bucket;
    }

    @Override
    public Bucket update(Bucket bucket) {
        for (Bucket b : Storage.buckets) {
            if (b.getId().equals(bucket.getId())) {
                b.setSelectedItems(bucket.getSelectedItems());
            }
        }
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
        for (Bucket b : Storage.buckets) {
            if (b.getId().equals(id)) {
                Storage.buckets.remove(b);
                return true;
            }
        }
        return false;
    }
}
