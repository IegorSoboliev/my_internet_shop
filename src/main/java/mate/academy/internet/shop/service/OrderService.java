package mate.academy.internet.shop.service;

import java.util.List;

import mate.academy.internet.shop.model.Item;
import mate.academy.internet.shop.model.Order;
import mate.academy.internet.shop.model.User;

public interface OrderService {

    Order create(Order order);

    Order update(Order order);

    Order get(Long id);

    List<Order> getAll();

    boolean delete(Order order);

    boolean deleteById(Long id);

    Order completeOrder(List<Item> items, User user);

    List<Order> getUserOrders(User user);
}
