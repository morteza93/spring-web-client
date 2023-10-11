package com.example.springwebclient.service.exception;

import com.example.springwebclient.common.BaseException;
import com.example.springwebclient.common.enumiration.ResultCode;
import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.NOT_ACCEPTABLE;

@ResponseStatus(code = NOT_ACCEPTABLE)
public class ConnectionException extends BaseException {
    @Override
    public Integer getErrorCode() {
        return ResultCode.FAILD.getErrorCode();
    }

    @Override
    public String getErrorKey() {
        return "error.network.fail";
    }
}
