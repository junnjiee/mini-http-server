/**
 * Exception that should be thrown when an invalid request is received
 **/
public class InvalidHttpRequestException extends Exception {
    public InvalidHttpRequestException() {
    }

    public InvalidHttpRequestException(String message) {
        super(message);
    }
}
