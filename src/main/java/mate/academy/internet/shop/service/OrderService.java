package mate.academy.internet.shop.service;

import java.util.List;

import mate.academy.internet.shop.exceptions.DataProcessingException;
import mate.academy.internet.shop.model.Item;
import mate.academy.internet.shop.model.Order;
import mate.academy.internet.shop.model.User;

public interface OrderService {

    Order update(Order order) throws DataProcessingException;

    Order get(Long id) throws DataProcessingException;

    List<Order> getAll() throws DataProcessingException;

    boolean deleteById(Long id) throws DataProcessingException;

    Order completeOrder(List<Item> items, User user) throws DataProcessingException;

    List<Order> getUserOrders(User user) throws DataProcessingException;
}
