package project.HelloBook.ExceptionHandlers;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import project.HelloBook.Dtos.ErrorResponse;
import project.HelloBook.ExceptionHandlers.Exceptions.*;

;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> handle(UserNotFoundException exception) {

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ErrorResponse
                        .builder()
                        .exception(UserNotFoundException.class.getSimpleName())
                        .message(exception.getMessage())
                        .build());
    }

    @ExceptionHandler(CanNotCreateException.class)
    public ResponseEntity<ErrorResponse> handle(CanNotCreateException exception) {

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ErrorResponse
                        .builder()
                        .exception(CanNotCreateException.class.getSimpleName())
                        .message(exception.getMessage())
                        .build());
    }

    @ExceptionHandler(CanNotUpdateException.class)
    public ResponseEntity<ErrorResponse> handle(CanNotUpdateException exception) {

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ErrorResponse
                        .builder()
                        .exception(CanNotUpdateException.class.getSimpleName())
                        .message(exception.getMessage())
                        .build());
    }

    @ExceptionHandler(AddressNotFoundException.class)
    public ResponseEntity<ErrorResponse> handle(AddressNotFoundException exception) {

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ErrorResponse
                        .builder()
                        .exception(AddressNotFoundException.class.getSimpleName())
                        .message(exception.getMessage())
                        .build());
    }

    @ExceptionHandler(AuthorNotFoundException.class)
    public ResponseEntity<ErrorResponse> handle(AuthorNotFoundException exception) {

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ErrorResponse
                        .builder()
                        .exception(AuthorNotFoundException.class.getSimpleName())
                        .message(exception.getMessage())
                        .build());
    }

    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<ErrorResponse> handle(BookNotFoundException exception) {

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ErrorResponse
                        .builder()
                        .exception(BookNotFoundException.class.getSimpleName())
                        .message(exception.getMessage())
                        .build());
    }

}
