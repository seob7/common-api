package io.github.seob7.example.error;

import io.github.seob7.BaseErrorCode;
import org.springframework.http.HttpStatus;

public enum ExampleErrorCode implements BaseErrorCode {

    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "User not found"),
    INVALID_REQUEST(HttpStatus.BAD_REQUEST, "Invalid request data");

    private final HttpStatus httpStatus;
    private final String message;

    ExampleErrorCode(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }

    @Override
    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
