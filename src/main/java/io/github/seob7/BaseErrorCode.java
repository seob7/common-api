package io.github.seob7;

import org.springframework.http.HttpStatus;

public interface BaseErrorCode {

    HttpStatus getHttpStatus();
    String getMessage();

}