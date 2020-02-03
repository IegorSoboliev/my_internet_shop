package mate.academy.internet.shop.service.impl;

import java.util.List;

import mate.academy.internet.shop.dao.OrderDao;
import mate.academy.internet.shop.exceptions.DataProcessingException;
import mate.academy.internet.shop.lib.Inject;
import mate.academy.internet.shop.lib.Service;
import mate.academy.internet.shop.model.Bucket;
import mate.academy.internet.shop.model.Item;
import mate.academy.internet.shop.model.Order;
import mate.academy.internet.shop.model.User;
import mate.academy.internet.shop.service.BucketService;
import mate.academy.internet.shop.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {
    @Inject
    private static OrderDao orderDao;
    @Inject
    private static BucketService bucketService;

    @Override
    public Order update(Order order) throws DataProcessingException {
        return orderDao.update(order);
    }

    @Override
    public Order get(Long id) throws DataProcessingException {
        return orderDao.get(id)
                .orElseThrow(() -> new DataProcessingException("Found no order with id " + id));
    }

    @Override
    public List<Order> getAll() throws DataProcessingException {
        return orderDao.getAll();
    }

    @Override
    public boolean deleteById(Long id) throws DataProcessingException {
        return orderDao.deleteById(id);
    }

    @Override
    public Order completeOrder(List<Item> items, User user) throws DataProcessingException {
        Order toComplete = new Order();
        Long userId = user.getId();
        toComplete.setUserId(userId);
        toComplete.setItems(items);
        orderDao.create(toComplete);
        Bucket bucket = bucketService.getByUserId(userId);
        bucketService.clear(bucket);
        return toComplete;
    }

    @Override
    public List<Order> getUserOrders(User user) throws DataProcessingException {
        return orderDao.getUserOrders(user.getId());
    }
}
