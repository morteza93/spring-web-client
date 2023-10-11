package com.example.springwebclient.service;

import com.example.springwebclient.common.BaseException;
import com.example.springwebclient.controller.dto.RequestDto;
import com.example.springwebclient.service.exception.ProviderException;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Validated
public interface ClientService {

    String clientInquiry(RequestDto requestDto) throws ProviderException;
}
