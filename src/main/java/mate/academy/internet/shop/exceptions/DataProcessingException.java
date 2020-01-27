package mate.academy.internet.shop.exceptions;

public class DataProcessingException extends Exception {

    public DataProcessingException(String message, Exception e) {
        super(message, e);
    }
}
