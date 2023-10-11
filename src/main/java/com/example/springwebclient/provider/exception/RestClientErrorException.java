package com.example.springwebclient.provider.exception;

import com.example.springwebclient.common.BaseRuntimeException;
import com.fasterxml.jackson.databind.JsonNode;
import com.tosantechno.basestructure.exception.BaseTcmRuntimeException;
import org.springframework.http.ResponseEntity;

public class RestClientErrorException extends BaseRuntimeException {
    final String responseBody;
    ResponseEntity responseEntity;
    final JsonNode jsonNode;
    public RestClientErrorException(String responseBody, JsonNode jsonNode) {
        super("");
        this.responseBody = responseBody;
        this.jsonNode = jsonNode;
    }

    public RestClientErrorException(ResponseEntity responseEntity) {
        this(null, null);
        this.responseEntity = responseEntity;
    }

    public String getResponseBody() {
        return responseBody;
    }

    public JsonNode getJsonNode() {
        return jsonNode;
    }
}
