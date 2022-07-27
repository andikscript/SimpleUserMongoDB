package com.andikscript.simpleusermongodb.handling;

public class RefreshTokenExpired extends Exception {
    public RefreshTokenExpired() {
    }

    public RefreshTokenExpired(String message) {
        super(message);
    }

    public RefreshTokenExpired(String message, Throwable cause) {
        super(message, cause);
    }

    public RefreshTokenExpired(Throwable cause) {
        super(cause);
    }

    public RefreshTokenExpired(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
