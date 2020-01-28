package mate.academy.internet.shop.dao.impl;

import java.util.List;
import java.util.Optional;

import mate.academy.internet.shop.database.Storage;
import mate.academy.internet.shop.lib.Dao;
import mate.academy.internet.shop.model.Order;

@Dao
public class OrderDaoImpl {

    public Order create(Order order) {
        Storage.orders.add(order);
        return order;
    }

    public Order update(Order order) {
        Storage.orders
                .stream()
                .filter(o -> o.getId().equals(order.getId()))
                .findFirst()
                .ifPresent(o -> o.setTotalPrice(order.getTotalPrice()));
        return order;
    }

    public Optional<Order> get(Long id) {
        return Storage.orders
                .stream()
                .filter(order -> order.getId().equals(id))
                .findFirst();
    }

    public List<Order> getUserOrders() {
        return Storage.orders;
    }

    public boolean delete(Order order) {
        return Storage.orders.remove(order);
    }

    public boolean deleteById(Long id) {
        return Storage.orders.removeIf(o -> o.getId().equals(id));
    }
}
