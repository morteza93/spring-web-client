package com.example.springwebclient.common;

import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

public abstract class BaseException extends Exception {

    private final Map<String, Object> messageArgs = new HashMap<>();

    public BaseException() {
    }

    protected BaseException(final Throwable cause) {
        super(cause);
    }


    public BaseException(final String message) {
        super(message);
    }


    public BaseException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public Object getMessageArg(final String key) {
        return messageArgs.get(key);
    }

    public void addMessageArg(final String messageArg, final Object messageVal) {
        this.messageArgs.put(messageArg, messageVal);
    }

    public Map<String, Object> getMessageArgs() {
        return messageArgs;
    }

    public HttpStatus getHttpStatus() {
        return HttpStatus.BAD_REQUEST;
    }

    public abstract Integer getErrorCode();

    public abstract String getErrorKey();
}
