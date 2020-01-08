package mate.academy.internet.shop.model;

public class User {
    private java.lang.Long id;
    private String name;
    private String email;

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public java.lang.Long getId() {
        return id;
    }

    public void setId(java.lang.Long userCounter) {
        this.id = userCounter;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "User{"
                + "id=" + id
                + ", name='" + name + '\''
                + ", email='" + email + '\''
                + '}';
    }
}
