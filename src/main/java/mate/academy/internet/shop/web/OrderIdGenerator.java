package mate.academy.internet.shop.web;

public class OrderIdGenerator {
    private static Long idGenerator = 1L;

    public static Long getIdGenerator() {
        return idGenerator++;
    }
}
