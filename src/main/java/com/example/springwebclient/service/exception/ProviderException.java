package com.example.springwebclient.service.exception;

import com.example.springwebclient.common.BaseException;
import com.example.springwebclient.common.enumiration.ResultCode;

/**
 * @author mor.nouri
 * created on 10/11/2023
 */

public class ProviderException extends BaseException {
    @Override
    public Integer getErrorCode() {
        return ResultCode.FAILD.getErrorCode();
    }

    @Override
    public String getErrorKey() {
        return "error.invalid.national.id";
    }
}
