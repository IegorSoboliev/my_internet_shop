package mate.academy.internet.shop.model;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private Long id;
    private Long userId;
    private List<Item> items = new ArrayList<>();
    private Double totalPrice;

    public Long getId() {
        return id;
    }

    public void setId(Long orderCounter) {
        this.id = orderCounter;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "Order{"
                + "id=" + id
                + ", userId=" + userId
                + ", items=" + items
                + ", totalPrice=" + totalPrice
                + '}';
    }
}
