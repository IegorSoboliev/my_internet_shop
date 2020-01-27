package mate.academy.internet.shop.service.impl;

import java.util.List;
import java.util.NoSuchElementException;

import mate.academy.internet.shop.dao.ItemDao;
import mate.academy.internet.shop.exceptions.DataProcessingException;
import mate.academy.internet.shop.lib.Inject;
import mate.academy.internet.shop.lib.Service;
import mate.academy.internet.shop.model.Item;
import mate.academy.internet.shop.service.ItemService;

@Service
public class ItemServiceImpl implements ItemService {
    @Inject
    private static ItemDao itemDao;

    @Override
    public Item create(Item item) throws DataProcessingException {
        return itemDao.create(item);
    }

    @Override
    public Item update(Item item) throws DataProcessingException {
        return itemDao.update(item);
    }

    @Override
    public Item get(Long id) throws DataProcessingException {
        return itemDao.get(id)
                .orElseThrow(() -> new NoSuchElementException("Found no item with id " + id));
    }

    @Override
    public List<Item> getAll() throws DataProcessingException {
        return itemDao.getAll();
    }

    @Override
    public boolean deleteById(Long id) throws DataProcessingException {
        return itemDao.deleteById(id);
    }
}
