package mate.academy.internet.shop.dao.impl;

import java.util.List;
import java.util.Optional;

import mate.academy.internet.shop.dao.ItemDao;
import mate.academy.internet.shop.database.Storage;
import mate.academy.internet.shop.lib.Dao;
import mate.academy.internet.shop.model.Item;

@Dao
public class ItemDaoImpl implements ItemDao {
    private static Long itemCounter = 1L;

    @Override
    public Item create(Item item) {
        item.setId(itemCounter);
        Storage.items.add(item);
        itemCounter++;
        return item;
    }

    @Override
    public Item update(Item item) {
        for (Item i : Storage.items) {
            if (i.getId().equals(item.getId())) {
                i.setName(i.getName());
                i.setPrice(i.getPrice());
            }
        }
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
        for (Item i : Storage.items) {
            if (i.getId().equals(id)) {
                Storage.items.remove(i);
                return true;
            }
        }
        return false;
    }
}
