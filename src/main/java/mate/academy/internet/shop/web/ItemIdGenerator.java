package mate.academy.internet.shop.web;

public class ItemIdGenerator {
    private static Long idGenerator = 1L;

    public static Long getIdGenerator() {
        return idGenerator++;
    }
}