package com.example.springwebclient.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Map;

/**
 * @author mor.nouri
 * created on 8/20/2023
 */

@Data
public class getTokenResponseDto {

    private String status;
    private String message;
    private String timestamp;
    @JsonProperty("tracking_number")
    private String trackingNumber;
    @JsonProperty("request_number")
    private String requestNumber;
    private Result result;
    private Map<String, Object> transactionDetails;
    private Error errors;

    @Data
    public static class Result {
        private String token;

        @JsonProperty("token_type")
        private String tokenType;

        @JsonProperty("client_id")
        private String clientId;

        @JsonProperty("expire_date")
        private String expireDate;

        private String scope;
    }

    @Data
    public static class Error {
        private String errorCode;
        private String errorDescription;
        private String referenceName;
        private String originalValue;
    }
}

