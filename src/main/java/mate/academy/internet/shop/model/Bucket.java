package mate.academy.internet.shop.model;

import java.util.List;

public class Bucket {
    private Long id;
    private User user;
    private List<Item> selectedItems;

    public Bucket(User user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long bucketCounter) {
        this.id = bucketCounter;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Item> getSelectedItems() {
        return selectedItems;
    }

    public void setSelectedItems(List<Item> selectedItems) {
        this.selectedItems = selectedItems;
    }

    @Override
    public String toString() {
        return "Bucket{"
                + "id=" + id
                + ", user=" + user
                + ", selectedItems=" + selectedItems
                + '}';
    }
}
