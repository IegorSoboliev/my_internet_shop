package mate.academy.internet.shop.dao.impl;

import java.util.List;
import java.util.Optional;

import mate.academy.internet.shop.dao.ItemDao;
import mate.academy.internet.shop.database.Storage;
import mate.academy.internet.shop.lib.Dao;
import mate.academy.internet.shop.model.Item;

@Dao
public class ItemDaoImpl implements ItemDao {

    @Override
    public Item create(Item item) {
        Storage.items.add(item);
        return item;
    }

    @Override
    public Item update(Item item) {
        Storage.items
                .stream()
                .filter(i -> i.getId().equals(item.getId()))
                .findFirst()
                .ifPresent(i -> i.setPrice(item.getPrice()));
        return item;
    }

    @Override
    public Optional<Item> get(Long id) {
        return Storage.items
                .stream()
                .filter(item -> item.getId().equals(id))
                .findFirst();
    }

    @Override
    public List<Item> getAll() {
        return Storage.items;
    }

    @Override
    public boolean delete(Item item) {
        return Storage.items.remove(item);
    }

    @Override
    public boolean deleteById(Long id) {
        return Storage.items.removeIf(i -> i.getId().equals(id));
    }
}
