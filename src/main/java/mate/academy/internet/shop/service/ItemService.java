package mate.academy.internet.shop.service;

import java.util.List;

import mate.academy.internet.shop.exceptions.DataProcessingException;
import mate.academy.internet.shop.model.Item;

public interface ItemService {

    Item create(Item item) throws DataProcessingException;

    Item update(Item item) throws DataProcessingException;

    Item get(Long id) throws DataProcessingException;

    List<Item> getAll() throws DataProcessingException;

    boolean deleteById(Long id) throws DataProcessingException;
}
