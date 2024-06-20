package med.voll.api.infrastructure.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;

@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity handle404() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handle400(MethodArgumentNotValidException exception) {
        var errors = exception.getFieldErrors();
        return ResponseEntity.badRequest().body(
                errors.stream()
                        .map(ValidationErrorData::new)
                        .toList());
    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    private ResponseEntity handleIntegrityConstraintViolation (SQLIntegrityConstraintViolationException e){
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .build();
    }

    private record ValidationErrorData (String filed, String message){
        public ValidationErrorData(FieldError fieldError) {
            this(fieldError.getField(), fieldError.getDefaultMessage());
        }
    }
}
