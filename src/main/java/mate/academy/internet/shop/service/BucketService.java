package mate.academy.internet.shop.service;

import java.util.List;

import mate.academy.internet.shop.model.Bucket;
import mate.academy.internet.shop.model.Item;

public interface BucketService {

    Bucket create(Bucket bucket);

    Bucket update(Bucket bucket);

    Bucket get(Long id);

    Bucket getByUserId(Long userId);

    boolean delete(Bucket bucket);

    boolean deleteById(Long id);

    void addItem(Bucket bucket, Item item);

    void deleteItem(Bucket bucket, Item item);

    void clear(Bucket bucket);

    List<Bucket> getAll();

    List<Item> getAllItems(Bucket bucket);
}
