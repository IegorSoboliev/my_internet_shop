package mate.academy.internet.shop.model;

import java.util.List;

public class Bucket {
    private Long id;
    private Long userId;
    private List<Item> items;

    public Bucket(Long userId) {
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long idGenerator) {
        this.id = idGenerator;
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

    @Override
    public String toString() {
        return "Bucket{"
                + "id=" + id
                + ", userId=" + userId
                + ", items=" + items
                + '}';
    }
}
