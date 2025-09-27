package io.github.seob7.example.error;

import io.github.seob7.Api;
import io.github.seob7.BaseErrorCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExampleExceptionHandler {

    /**
     * Handles custom exceptions that implement BaseErrorCode.
     */
    @ExceptionHandler(CustomExampleException.class)
    public ResponseEntity<Api<BaseErrorCode>> handleCustomException(CustomExampleException e) {
        System.out.println("Custom exception occurred" + e);
        return Api.ERROR(e.getErrorCode());
    }

}
