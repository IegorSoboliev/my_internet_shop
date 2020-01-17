package mate.academy.internet.shop.service.impl;

import java.util.List;
import java.util.NoSuchElementException;

import mate.academy.internet.shop.dao.BucketDao;
import mate.academy.internet.shop.lib.Inject;
import mate.academy.internet.shop.lib.Service;
import mate.academy.internet.shop.model.Bucket;
import mate.academy.internet.shop.model.Item;
import mate.academy.internet.shop.service.BucketService;
import mate.academy.internet.shop.web.BucketIdGenerator;

@Service
public class BucketServiceImpl implements BucketService {
    @Inject
    private static BucketDao bucketDao;

    @Override
    public Bucket create(Bucket bucket) {
        bucket.setId(BucketIdGenerator.getIdGenerator());
        return bucketDao.create(bucket);
    }

    @Override
    public Bucket update(Bucket bucket) {
        return bucketDao.update(bucket);
    }

    @Override
    public Bucket get(Long id) {
        return bucketDao.get(id)
                .orElseThrow(() -> new NoSuchElementException("Found no bucket with id " + id));
    }

    @Override
    public Bucket getByUserId(Long userId) {
        Bucket bucket = bucketDao
                .getAll()
                .stream()
                .filter(b -> b.getUserId().equals(userId))
                .findFirst()
                .orElse(bucketDao.create(new Bucket()));
        bucket.setUserId(userId);
        return bucket;
    }

    @Override
    public boolean delete(Bucket bucket) {
        return bucketDao.delete(bucket);
    }

    @Override
    public boolean deleteById(Long id) {
        return bucketDao.deleteById(id);
    }

    @Override
    public void addItem(Bucket bucket, Item item) {
        bucketDao.get(bucket.getId()).get().getItems().add(item);
    }

    @Override
    public void deleteItem(Bucket bucket, Item item) {
        bucketDao.get(bucket.getId()).get().getItems().remove(item);
    }

    @Override
    public void clear(Bucket bucket) {
        bucketDao.get(bucket.getId()).get().getItems().clear();
    }

    @Override
    public List<Bucket> getAll() {
        return bucketDao.getAll();
    }

    @Override
    public List<Item> getAllItems(Bucket bucket) {
        return bucketDao.get(bucket.getId()).get().getItems();
    }
}
