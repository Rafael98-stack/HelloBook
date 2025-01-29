package project.HelloBook.ExceptionHandlers.Exceptions;

public class CanNotCreateException extends RuntimeException {
    public CanNotCreateException(String message) {
        super(message);
    }
}
