package mate.academy.internet.shop.service;

import mate.academy.internet.shop.model.Bucket;
import mate.academy.internet.shop.model.Item;

import java.util.List;
import java.util.Optional;

public interface BucketService {

    Bucket create(Bucket bucket);

    Bucket update(Bucket bucket);

    Bucket get(Long id);

    boolean delete(Bucket bucket);

    boolean deleteById(Long id);

    void addItem(Bucket bucket, Item item);

    void deleteItem(Bucket bucket, Item item);

    void clear(Bucket bucket);

    List<Item> getAllItems(Bucket bucket);
}
