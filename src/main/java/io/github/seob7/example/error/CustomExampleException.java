package io.github.seob7.example.error;

import io.github.seob7.BaseErrorCode;

public class CustomExampleException extends RuntimeException {
    private final BaseErrorCode errorCode;

    public CustomExampleException(BaseErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public BaseErrorCode getErrorCode() {
        return errorCode;
    }
}