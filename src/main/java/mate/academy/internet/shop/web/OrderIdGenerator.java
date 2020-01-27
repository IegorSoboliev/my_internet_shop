package mate.academy.internet.shop.web;

public class OrderIdGenerator {
    private static Long idGenerator = 0L;

    public static Long getIdGenerator() {
        return idGenerator++;
    }
}
