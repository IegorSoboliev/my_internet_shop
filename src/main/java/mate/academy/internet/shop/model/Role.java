package mate.academy.internet.shop.model;

import mate.academy.internet.shop.web.RoleIdGenerator;

public class Role {
    private final Long ID;
    private RoleName roleName;

    public Role() {
        this.ID = RoleIdGenerator.getIdGenerator();
    }

    public Role(RoleName roleName) {
        this();
        this.roleName = roleName;
    }

    public enum RoleName {
        USER, ADMIN
    }

    public Long getID() {
        return ID;
    }

    public RoleName getRoleName() {
        return roleName;
    }

    public void setRoleName(RoleName roleName) {
        this.roleName = roleName;
    }

    public static Role of(String roleName) {
        return new Role(RoleName.valueOf(roleName));
    }

    @Override
    public String toString() {
        return "Role{" + "roleName=" + roleName + '}';
    }
}
