package mate.academy.internet.shop.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Bucket {
    private Long id;
    private Long userId;
    private List<Item> items = new ArrayList<>();

    public Bucket() {
    }

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
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Bucket bucket = (Bucket) o;
        return id.equals(bucket.id) && userId.equals(bucket.userId) && items.equals(bucket.items);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, items);
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
