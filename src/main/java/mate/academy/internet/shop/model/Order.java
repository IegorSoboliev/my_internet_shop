package mate.academy.internet.shop.model;

import java.util.List;

public class Order {
    private Long id;
    private User user;
    private List<Item> items;
    private Bucket bucket;
    private Double totalPrice;

    public Order(User user, List<Item> items) {
        this.user = user;
        items = bucket.getSelectedItems();
        this.items = items;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long orderCounter) {
        this.id = orderCounter;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Bucket getBucket() {
        return bucket;
    }

    public void setBucket(Bucket bucket) {
        this.bucket = bucket;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", user=" + user +
                ", items=" + items +
                ", bucket=" + bucket +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
