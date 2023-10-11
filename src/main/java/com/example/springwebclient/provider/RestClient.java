package com.example.springwebclient.provider;


import com.example.springwebclient.service.exception.ConnectionException;
import com.example.springwebclient.service.exception.TimeoutException;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;

import java.net.URI;

public interface RestClient {
    <T> T post(String pathName, HttpHeaders headers, Object requestParams, ParameterizedTypeReference<T> typeReference) throws TimeoutException, ConnectionException;
    <T> T post(URI uri, HttpHeaders headers, Object requestParams, ParameterizedTypeReference<T> typeReference) throws TimeoutException, ConnectionException;
}
