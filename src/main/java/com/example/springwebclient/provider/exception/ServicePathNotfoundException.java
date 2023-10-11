package com.example.springwebclient.provider.exception;


import com.example.springwebclient.common.BaseRuntimeException;

public class ServicePathNotfoundException extends BaseRuntimeException {

    public ServicePathNotfoundException(String pathName){
        super("");
    }
}
