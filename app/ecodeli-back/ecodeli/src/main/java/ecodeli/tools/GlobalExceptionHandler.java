package ecodeli.tools;

import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.ArrayList;
import java.util.Arrays;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Pour les erreurs de @Valid sur les DTO
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationExceptions(MethodArgumentNotValidException exceptions) {
        ResponseErrorV2 errors = new ResponseErrorV2();

        for (FieldError error : exceptions.getBindingResult().getFieldErrors()) {
            errors.getErrors().computeIfAbsent(error.getField(), key -> new ArrayList<>()).add(error.getDefaultMessage());
        }

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    //Pour les erreurs que la bdd pourrait nous remonter
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Object> handleDuplicateKey(DataIntegrityViolationException exception) {
        String message = "A database constraint was violated.";

        if (exception.getMessage() != null && exception.getMessage().contains("users_email_key")) {
            message = "Email already exists !";
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ResponseErrorV2("email", message));
        }

        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ResponseErrorV2("global error", message));
    }

    //Pour les erreurs sur body manquant ou mal formé des requêtes
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException exception) {
        ResponseErrorV2 error = new ResponseErrorV2();
        if (exception.getCause() instanceof MismatchedInputException) {
            error.add("body", "The request body is present but contains invalid or malformed data.");
        } else {
            error.add("body", "The request body is required but is missing.");
        }
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    //pour les requestsParam associé à des types enum incorrecte (et autres types)
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ResponseErrorV2> handleTypeMismatch(MethodArgumentTypeMismatchException exception) {
        Class<?> requiredType = exception.getRequiredType();
        String paramName = exception.getName();
        if (requiredType != null && requiredType.isEnum()) {
            Object[] enumValues = requiredType.getEnumConstants();
            String invalidValue = String.valueOf(exception.getValue());
            return ResponseEntity.badRequest().body(
                    new ResponseErrorV2(paramName,
                            "The value '" + invalidValue + "' is not valid for the parameter '" + paramName +
                            "'. The possible values are : " + Arrays.toString(enumValues)
            ));
        }
        return ResponseEntity.badRequest().body(new ResponseErrorV2(paramName,
                "Error to convert '" + paramName + "': " + exception.getMessage()
        ));
    }
}
