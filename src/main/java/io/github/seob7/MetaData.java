package io.github.seob7;

public class MetaData {
    private final boolean success;
    private final int code;
    private final String message;

    public MetaData(boolean success, int code, String message) {
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