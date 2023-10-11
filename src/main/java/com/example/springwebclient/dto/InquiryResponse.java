package com.example.springwebclient.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;


/**
 * @author mor.nouri
 * created on 8/20/2023
 */

@Data
public class InquiryResponse {
    private String message;
    private String timestamp;
    @JsonProperty("tracking_number")
    private String trackingNumber;
    @JsonProperty("request_number")
    private String requestNumber;
    private Result result;
    private Error errors;

    @Data
    public static class Result {
        @JsonProperty("reference_number")
        private String passportId;
    }

    @Data
    public static class Error {
        private String errorCode;
        private String errorDescription;
        private String referenceName;
        private String originalValue;
    }
}



