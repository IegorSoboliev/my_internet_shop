package mate.academy.internet.shop.dao;

import java.util.List;
import java.util.Optional;

import mate.academy.internet.shop.exceptions.DataProcessingException;
import mate.academy.internet.shop.model.Item;

public interface ItemDao {

    Item create(Item item) throws DataProcessingException;

    Item update(Item item) throws DataProcessingException;

    Optional<Item> get(Long id) throws DataProcessingException;

    List<Item> getAll() throws DataProcessingException;

    boolean deleteById(Long id) throws DataProcessingException;
}
