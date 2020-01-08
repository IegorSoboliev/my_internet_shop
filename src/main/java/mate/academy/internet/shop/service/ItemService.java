package mate.academy.internet.shop.service;

import mate.academy.internet.shop.model.Item;

import java.util.List;
import java.util.Optional;

public interface ItemService {

    Item create(Item item);

    Item update(Item item);

    Item get(Long id);

    List<Item> getAllItems();

    boolean delete(Item item);

    boolean deleteById(Long id);
}
