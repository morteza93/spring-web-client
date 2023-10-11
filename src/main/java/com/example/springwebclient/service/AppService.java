package com.example.springwebclient.service;

import com.example.springwebclient.common.enumiration.ResultCode;
import com.example.springwebclient.controller.dto.RequestDto;
import com.example.springwebclient.controller.dto.ResponseDto;
import com.example.springwebclient.service.exception.ProviderException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author mor.nouri
 * created on 10/11/2023
 */

@Service
public class AppService {
    @Autowired
    ClientServiceImpl clientService;

    public ResponseDto doInquiry(RequestDto requestDto) {

        ResponseDto responseDto;
        try {
            responseDto = ResponseDto.builder()
                    .passportId(clientService.clientInquiry(requestDto))
                    .trackingNumber(requestDto.getGuid())
                    .error(ResultCode.SUCCEED)
                    .build();

        } catch (ProviderException e) {
            responseDto = ResponseDto.builder()
                    .trackingNumber(requestDto.getGuid())
                    .error(ResultCode.FAILD)
                    .build();
        }
        return responseDto;
    }
}
