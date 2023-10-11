package com.example.springwebclient.controller.dto;

import com.example.springwebclient.common.enumiration.ResultCode;
import lombok.Builder;
import lombok.Data;

/**
 * @author mor.nouri
 * created on 10/11/2023
 */

@Data
@Builder
public class ResponseDto {
    String passportId;
    String trackingNumber;
    ResultCode error;
}
