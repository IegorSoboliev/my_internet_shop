package mate.academy.internet.shop.model;

import java.util.List;

public class Order {
    private java.lang.Long id;
    private User user;
    private List<Item> items;
    private Double totalPrice;

    public Order(User user, List<Item> items) {
        this.user = user;
        this.items = items;
    }

    public java.lang.Long getId() {
        return id;
    }

    public void setId(java.lang.Long orderCounter) {
        this.id = orderCounter;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
                + ", user=" + user
                + ", items=" + items
                + ", totalPrice=" + totalPrice
                + '}';
    }
}
