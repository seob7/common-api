package io.github.seob7;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class Api<T> {

    private final MetaData metaData;
    private final T data;

    public Api(MetaData metaData, T data) {
        this.metaData = metaData;
        this.data = data;
    }

    public MetaData getMetaData() {
        return metaData;
    }

    public T getData() {
        return data;
    }

    public static <T> ResponseEntity<Api<T>> OK(T data, String message) {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(new Api<>(
                new MetaData(true, HttpStatus.OK.value(), message),
                data
            ));
    }

    public static <T> ResponseEntity<Api<T>> OK(T data) {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(new Api<>(
                new MetaData(true, HttpStatus.OK.value(), null),
                data
            ));
    }

    public static <T> ResponseEntity<Api<T>> OK(T data, HttpStatus httpStatus) {
        return ResponseEntity
            .status(httpStatus)
            .body(new Api<>(
                new MetaData(true, httpStatus.value(), null),
                data
            ));
    }

    public static <T> ResponseEntity<Api<T>> OK(T data, String message, HttpStatus httpStatus) {
        return ResponseEntity
            .status(httpStatus)
            .body(new Api<>(
                new MetaData(true, httpStatus.value(), message),
                data
            ));
    }

    public static ResponseEntity<Api<BaseErrorCode>> ERROR(BaseErrorCode baseErrorCode) {
        return ResponseEntity
            .status(baseErrorCode.getHttpStatus())
            .body(new Api<>(
                new MetaData(false, baseErrorCode.getHttpStatus().value(), baseErrorCode.getMessage()),
                null
            ));
    }

    public static ResponseEntity<Api<String>> ERROR(String message, HttpStatus httpStatus) {
        return ResponseEntity
            .status(httpStatus)
            .body(new Api<>(
                new MetaData(false, httpStatus.value(), message),
                null
            ));
    }

}