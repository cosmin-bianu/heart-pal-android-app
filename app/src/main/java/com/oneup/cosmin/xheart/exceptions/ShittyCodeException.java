package com.oneup.cosmin.xheart.exceptions;

public class ShittyCodeException extends Exception {
    public ShittyCodeException() {
    }

    public ShittyCodeException(String message) {
        super(message);
    }

    public ShittyCodeException(String message, Throwable cause) {
        super(message, cause);
    }
    public ShittyCodeException(Throwable cause) {
        super(cause);
    }
}
