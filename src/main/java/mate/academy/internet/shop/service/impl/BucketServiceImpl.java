package mate.academy.internet.shop.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import mate.academy.internet.shop.dao.BucketDao;
import mate.academy.internet.shop.exceptions.DataProcessingException;
import mate.academy.internet.shop.lib.Inject;
import mate.academy.internet.shop.lib.Service;
import mate.academy.internet.shop.model.Bucket;
import mate.academy.internet.shop.model.Item;
import mate.academy.internet.shop.service.BucketService;

@Service
public class BucketServiceImpl implements BucketService {
    @Inject
    private static BucketDao bucketDao;

    @Override
    public Bucket create(Bucket bucket) throws DataProcessingException {
        return bucketDao.create(bucket);
    }

    @Override
    public Bucket update(Bucket bucket) throws DataProcessingException {
        return bucketDao.update(bucket);
    }

    @Override
    public Bucket get(Long id) throws DataProcessingException {
        return bucketDao.get(id)
                .orElseThrow(() -> new DataProcessingException("Found no bucket with id " + id));
    }

    @Override
    public Bucket getByUserId(Long userId) throws DataProcessingException {
        Optional<Bucket> bucket = bucketDao.getByUserId(userId);
        if (bucket.isPresent()) {
            return bucket.get();
        }
        return bucketDao.create(new Bucket(userId));
    }

    @Override
    public boolean deleteById(Long id) throws DataProcessingException {
        return bucketDao.deleteById(id);
    }

    @Override
    public void addItem(Bucket bucket, Item item) throws DataProcessingException {
        bucket.getItems().add(item);
        bucketDao.update(bucket);
    }

    @Override
    public void deleteItem(Bucket bucket, Item item) throws DataProcessingException {
        bucket.getItems().remove(item);
        bucketDao.update(bucket);
    }

    @Override
    public void clear(Bucket bucket) throws DataProcessingException {
        bucketDao.removeBucketItemsFromDB(bucket);
        bucket.setItems(new ArrayList<>());
    }

    @Override
    public List<Bucket> getAll() throws DataProcessingException {
        return bucketDao.getAll();
    }
}
