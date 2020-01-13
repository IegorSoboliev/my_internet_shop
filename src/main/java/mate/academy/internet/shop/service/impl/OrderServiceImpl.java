package mate.academy.internet.shop.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import mate.academy.internet.shop.dao.OrderDao;
import mate.academy.internet.shop.lib.Inject;
import mate.academy.internet.shop.lib.Service;
import mate.academy.internet.shop.model.Item;
import mate.academy.internet.shop.model.Order;
import mate.academy.internet.shop.model.User;
import mate.academy.internet.shop.service.OrderService;

@Service
public class  OrderServiceImpl implements OrderService {
    @Inject
    public static OrderDao orderDao;

    @Override
    public Order create(Order order) {
        return orderDao.create(order);
    }

    @Override
    public Order update(Order order) {
        return orderDao.update(order);
    }

    @Override
    public Order get(java.lang.Long id) {
        return orderDao.get(id)
                .orElseThrow(() -> new NoSuchElementException("Found no order with id " + id));
    }

    @Override
    public List<Order> getAll() {
        return orderDao.getAll();
    }

    @Override
    public boolean delete(Order order) {
        return orderDao.delete(order);
    }

    @Override
    public boolean deleteById(java.lang.Long id) {
        return orderDao.deleteById(id);
    }

    @Override
    public Order completeOrder(List<Item> items, User user) {
        Order newOrder = new Order();
        newOrder.setUserId(user.getId());
        newOrder.setItems(items);
        orderDao.create(newOrder);
        return newOrder;
    }

    @Override
    public List<Order> getUserOrders(User user) {
        List<Order> userOrders = new ArrayList<>();
        for (Order o : orderDao.getAll()) {
            if (o.getUserId().equals(user.getId())) {
                userOrders.add(o);
            }
        }
        return userOrders;
    }
}
