package mate.academy.internet.shop.dao;

import java.util.List;
import java.util.Optional;

import mate.academy.internet.shop.model.Order;

public interface OrderDao {

    Order create(Order order);

    Order update(Order order);

    Optional<Order> get(Long id);

    List<Order> getAll();

    boolean delete(Order order);

    boolean deleteById(Long id);
}
