package ru.romanov.marketplace.productservice.exception;

import io.micrometer.tracing.Span;
import io.micrometer.tracing.Tracer;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.OffsetDateTime;

import static ru.romanov.marketplace.productservice.utils.MessageUtils.EXCEPTION_HANDLER_RESPONSE;

@RestControllerAdvice
@RequiredArgsConstructor
public class ProductExceptionHandler {

    private final Tracer tracer;

    @ExceptionHandler(ProductException.CreationException.class)
    public ResponseEntity<ErrorResponse> handleCreationException(
            final ProductException.CreationException exception,
            WebRequest request
    ) {
        return new ResponseEntity<>(errorResponse(request), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ProductException.NotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFoundException(
            final ProductException.NotFoundException exception,
            WebRequest request
    ) {
        return new ResponseEntity<>(errorResponse(request), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ProductException.ConversionException.class)
    public ResponseEntity<ErrorResponse> handleConversionException(
            final ProductException.ConversionException exception,
            WebRequest request
    ) {
        return new ResponseEntity<>(errorResponse(request), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ProductException.UpdateException.class)
    public ResponseEntity<ErrorResponse> handleUpdateException(
            final ProductException.UpdateException exception,
            WebRequest request
    ) {
        return new ResponseEntity<>(errorResponse(request), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ProductException.DeletedException.class)
    public ResponseEntity<ErrorResponse> handleUpdateException(
            final ProductException.DeletedException exception,
            WebRequest request
    ) {
        return new ResponseEntity<>(errorResponse(request), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(
            final Exception exception,
            WebRequest request
    ) {
        return new ResponseEntity<>(errorResponse(request), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ErrorResponse errorResponse(WebRequest request) {
        String path = request.getDescription(false).replace("uri=", "");
        String timestamp = OffsetDateTime.now().toString();

        Span currentSpan = tracer.currentSpan();
        String traceId = currentSpan.context().traceId();

        return new ErrorResponse(
                EXCEPTION_HANDLER_RESPONSE,
                timestamp,
                path,
                traceId
        );
    }
}
