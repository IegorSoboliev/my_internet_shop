package mate.academy.internet.shop.service.impl;

import mate.academy.internet.shop.dao.BucketDao;
import mate.academy.internet.shop.lib.Inject;
import mate.academy.internet.shop.lib.Service;
import mate.academy.internet.shop.model.Bucket;
import mate.academy.internet.shop.model.Item;
import mate.academy.internet.shop.service.BucketService;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class BucketServiceImpl implements BucketService {
    @Inject
    private static BucketDao bucketDao;

    @Override
    public Bucket create(Bucket bucket) {
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
    public boolean delete(Bucket bucket) {
        return bucketDao.delete(bucket);
    }

    @Override
    public boolean deleteById(Long id) {
        return bucketDao.deleteById(id);
    }

    @Override
    public void addItem(Bucket bucket, Item item) {
        bucketDao.get(bucket.getId()).get().getSelectedItems().add(item);
    }

    @Override
    public void deleteItem(Bucket bucket, Item item) {
        bucketDao.get(bucket.getId()).get().getSelectedItems().remove(item);
    }

    @Override
    public void clear(Bucket bucket) {
        bucketDao.get(bucket.getId()).get().getSelectedItems().clear();
    }

    @Override
    public List<Item> getAllItems(Bucket bucket) {
        return bucketDao.get(bucket.getId()).get().getSelectedItems();
    }
}
