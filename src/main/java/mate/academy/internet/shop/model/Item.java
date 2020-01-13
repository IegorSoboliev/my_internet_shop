package mate.academy.internet.shop.model;

public class Item {
    private Long id;
    private String name;
    private Integer price;

    public Long getId() {
        return id;
    }

    public void setId(Long itemCounter) {
        this.id = itemCounter;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Item{"
                + "id=" + id
                + ", name='" + name + '\''
                + ", price=" + price
                + '}';
    }
}
