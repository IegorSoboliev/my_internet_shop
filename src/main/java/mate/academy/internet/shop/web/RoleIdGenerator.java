package mate.academy.internet.shop.web;

public class RoleIdGenerator {
    private static Long idGenerator = 1L;

    public static Long getIdGenerator() {
        return idGenerator++;
    }
}
