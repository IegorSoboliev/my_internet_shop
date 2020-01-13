package mate.academy.internet.shop.model;

public class User {
    private java.lang.Long id;
    private String name;
    private String surname;
    private String email;
    private String password;

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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
