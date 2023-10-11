package com.example.springwebclient.common;

public class BaseRuntimeException extends RuntimeException {

    protected BaseRuntimeException(final Throwable cause) {
        super(cause);
    }

    public BaseRuntimeException(final String message) {
        super(message);
    }

    public BaseRuntimeException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
