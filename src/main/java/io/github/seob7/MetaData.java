package io.github.seob7;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class MetaData {
    private final boolean success;
    private final int code;
    private final String message;

    @JsonCreator
    public MetaData(
        @JsonProperty("success") boolean success,
        @JsonProperty("code") int code,
        @JsonProperty("message") String message
    ) {
        this.success = success;
        this.code = code;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}