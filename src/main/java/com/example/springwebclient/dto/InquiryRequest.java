package com.example.springwebclient.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

/**
 * @author mor.nouri
 * created on 8/20/2023
 */

@Getter
@Builder
public class InquiryRequest {

    @JsonProperty("first_Name")
    String name;

    String lastName;

    String passId;

    @JsonProperty("tracking_number")
    String trackingNumber;
}
