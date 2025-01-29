package project.HelloBook.ExceptionHandlers.Exceptions;

public class CanNotUpdateException extends RuntimeException {
    public CanNotUpdateException(String message) {
        super(message);
    }
}
