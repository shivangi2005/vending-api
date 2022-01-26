package com.oracle.vending.api.exception;

public class NotEnoughChangeException extends RuntimeException{
    public NotEnoughChangeException() {
    }

    public NotEnoughChangeException(String message) {
        super(message);
    }

    public NotEnoughChangeException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotEnoughChangeException(Throwable cause) {
        super(cause);
    }

    public NotEnoughChangeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
