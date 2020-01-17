package mate.academy.internet.shop.web;

public class BucketIdGenerator {
    private static Long idGenerator = 1L;

    public BucketIdGenerator() {
    }

    public static Long getIdGenerator() {
        return idGenerator++;
    }
}
