package ru.romanov.marketplace.productservice.rest.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.romanov.marketplace.productservice.service.ServiceException;

import java.time.OffsetDateTime;

@RestControllerAdvice
public class ProductExceptionHandler {

    private final static String MESSAGE = "Server error. Please contact with Product-service support";

    @ExceptionHandler(ServiceException.CreationException.class)
    public ResponseEntity<ErrorResponse> handleCreationException(
            final ServiceException.CreationException exception,
            WebRequest request
    ) {
        String path = request.getDescription(false).replace("uri", "");
        String timestamp = OffsetDateTime.now().toString();
        ErrorResponse errorResponse = new ErrorResponse(
                MESSAGE,
                timestamp,
                path
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ServiceException.NotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFoundException(
            final ServiceException.NotFoundException exception,
            WebRequest request
    ) {
        String path = request.getDescription(false).replace("uri=", "");
        String timestamp = OffsetDateTime.now().toString();
        ErrorResponse errorResponse = new ErrorResponse(
                MESSAGE,
                timestamp,
                path
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ServiceException.ConversionException.class)
    public ResponseEntity<ErrorResponse> handleConversionException(
            final ServiceException.ConversionException exception,
            WebRequest request
    ) {
        String path = request.getDescription(false).replace("uri=", "");
        String timestamp = OffsetDateTime.now().toString();
        ErrorResponse errorResponse = new ErrorResponse(
                MESSAGE,
                timestamp,
                path
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ServiceException.NotExistException.class)
    public ResponseEntity<ErrorResponse> handleNotExistException(
            final ServiceException.NotExistException exception,
            WebRequest request
    ) {
        String path = request.getDescription(false).replace("uri=", "");
        String timestamp = OffsetDateTime.now().toString();
        ErrorResponse errorResponse = new ErrorResponse(
                MESSAGE,
                timestamp,
                path
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
