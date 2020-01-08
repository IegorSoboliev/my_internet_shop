package mate.academy.internet.shop.dao;

import mate.academy.internet.shop.model.Item;

import java.util.List;
import java.util.Optional;

public interface ItemDao {

    Item create(Item item);

    Item update(Item item);

    Optional<Item> get(Long id);

    List<Item> getAll();

    boolean delete(Item item);

    boolean deleteById(Long id);
}
