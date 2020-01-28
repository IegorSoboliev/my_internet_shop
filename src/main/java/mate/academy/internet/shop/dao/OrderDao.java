package mate.academy.internet.shop.dao;

import java.util.List;
import java.util.Optional;

import mate.academy.internet.shop.exceptions.DataProcessingException;
import mate.academy.internet.shop.model.Order;

public interface OrderDao {

    Order create(Order order) throws DataProcessingException;

    Order update(Order order) throws DataProcessingException;

    Optional<Order> get(Long id) throws DataProcessingException;

    List<Order> getUserOrders(Long userId) throws DataProcessingException;

    boolean deleteById(Long id) throws DataProcessingException;

    List<Order> getAll() throws DataProcessingException;
}
