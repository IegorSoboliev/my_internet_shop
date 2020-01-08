package mate.academy.internet.shop.dao.impl;

import java.util.List;
import java.util.Optional;

import mate.academy.internet.shop.dao.OrderDao;
import mate.academy.internet.shop.database.Storage;
import mate.academy.internet.shop.lib.Dao;
import mate.academy.internet.shop.model.Order;

@Dao
public class OrderDaoImpl implements OrderDao {
    private static Long idGenerator = 1L;

    @Override
    public Order create(Order order) {
        order.setId(idGenerator);
        Storage.orders.add(order);
        idGenerator++;
        return order;
    }

    @Override
    public Order update(Order order) {
        for (Order o : Storage.orders) {
            if (o.getId().equals(order.getId())) {
                o.setTotalPrice(order.getTotalPrice());
            }
        }
        return order;
    }

    @Override
    public Optional<Order> get(Long id) {
        return Storage.orders
                .stream()
                .filter(order -> order.getId().equals(id))
                .findFirst();
    }

    @Override
    public List<Order> getAll() {
        return Storage.orders;
    }

    @Override
    public boolean delete(Order order) {
        return Storage.orders.remove(order);
    }

    @Override
    public boolean deleteById(Long id) {
        for (Order o : Storage.orders) {
            if (o.getId().equals(id)) {
                Storage.orders.remove(o);
                return true;
            }
        }
        return false;
    }
}
