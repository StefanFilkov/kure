package bg.tuvarna.sit.kontrl1.exception;

import bg.tuvarna.sit.kontrl1.model.ErrorDetail;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;


@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        return getErrorDetailResponseEntity(exception, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<Object> handleResponseStatusException(ResponseStatusException exception) {
        return getErrorDetailResponseEntity(exception, HttpStatus.NOT_FOUND );
    }

    private static ResponseEntity<Object> getErrorDetailResponseEntity(Exception exception, HttpStatus status) {
        ErrorDetail errorDetails = ErrorDetail
                .builder()
                .time(LocalDateTime.now())
                .message(exception.getMessage())
                .build();
        return ResponseEntity.status(status).body(errorDetails);
    }
}
