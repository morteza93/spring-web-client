package com.example.springwebclient.common.enumiration;

/**
 * @author mor.nouri
 * created on 10/11/2023
 */

public enum ResultCode {
    SUCCEED(00),
    FAILD(-1);

    private int errorCode;

    ResultCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public ResultCode setErrorCode(int errorCode) {
        this.errorCode = errorCode;
        return this;
    }
}
