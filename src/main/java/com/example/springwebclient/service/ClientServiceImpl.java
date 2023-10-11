package com.example.springwebclient.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.springwebclient.common.ExternalServiceConfig;

import com.example.springwebclient.controller.dto.RequestDto;
import com.example.springwebclient.dto.InquiryRequest;
import com.example.springwebclient.dto.InquiryResponse;
import com.example.springwebclient.dto.getTokenResponseDto;
import com.example.springwebclient.provider.RestClient;
import com.example.springwebclient.provider.RestClientFactory;
import com.example.springwebclient.provider.exception.RestClientErrorException;
import com.example.springwebclient.service.exception.ConnectionException;
import com.example.springwebclient.service.exception.ProviderException;
import com.example.springwebclient.service.exception.TimeoutException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

/**
 * @author mor.nouri
 * created on 8/21/2023
 */

@Service
public class ClientServiceImpl implements ClientService {

    public static final Logger logger = LoggerFactory.getLogger(ClientServiceImpl.class);
    private final ExternalServiceConfig apiConfig;
    private final RestClient restClient;
    private long localExpirationTime = 0L;
    private DecodedJWT jwtToken;

    // TODO
    public ClientServiceImpl(@Qualifier("manaApiConfig") ExternalServiceConfig apiConfig, RestClientFactory restClientFactory) {
        this.apiConfig = apiConfig;
        this.restClient = restClientFactory.restClient(apiConfig);
    }

    public void getToken() throws ConnectionException, TimeoutException {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setBasicAuth(apiConfig.getUsername(), apiConfig.getPassword());
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);


            MultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
            requestParams.add("grant_type", "client_credentials");

            logger.info("The request sent to the Mana server");
            ParameterizedTypeReference<getTokenResponseDto> typeReference = new ParameterizedTypeReference<>() {
            };
            getTokenResponseDto responseDto = restClient.post("getTokenPath", headers, requestParams, typeReference);
            if (responseDto != null) {
                if (responseDto.getResult() != null && responseDto.getResult().getToken() != null) {
                    jwtToken = JWT.decode(responseDto.getResult().getToken());
                    localExpirationTime = evaluateExpireTime(System.currentTimeMillis());
                }
            } else {
                logger.error("Invalid response from get_token service!");
            }
        } catch (IllegalArgumentException e) {
            logger.error("fail to encode basic authentication");
        } catch (JWTDecodeException e) {
            logger.error("The JWT token is invalid! The incorrect retrieved token: {}", e.getLocalizedMessage());
        }
    }

    // Evaluate expire time locally (because of difference in time zone).
    private long evaluateExpireTime(long localCurrentTime) {
        long liveTime = jwtToken.getExpiresAt().getTime() - jwtToken.getIssuedAt().getTime();
        return localCurrentTime + liveTime;
    }

    private String handleToken() throws ConnectionException, TimeoutException {
        if (jwtToken == null || System.currentTimeMillis() <= localExpirationTime - 10) {
            synchronized (this) {
                if (System.currentTimeMillis() >= localExpirationTime - 10)
                    getToken();
            }
        }
        return jwtToken.getToken();
    }

    @Override
    public String clientInquiry(RequestDto requestDto) throws ProviderException {

        try {
            HttpHeaders headers = new HttpHeaders();
            String token = handleToken();
            headers.setBearerAuth(token);

            InquiryRequest payLoad = InquiryRequest.builder()
                    .trackingNumber(requestDto.getGuid())
                    .name(requestDto.getName())
                    .lastName(requestDto.getLastName())
                    .build();
            InquiryResponse responseBody = restClient.post("cardInquiryPath", headers, payLoad, new ParameterizedTypeReference<>() {
            });
            if (responseBody.getResult() != null && responseBody.getResult().getPassportId() != null) {
                logger.debug("The identify data you sent assigned to the {} passport number", responseBody.getResult().getPassportId());
                return responseBody.getResult().getPassportId();
            } else {
                logger.error("There is no passport id assigned to data of the request by tracking number '{}'.", responseBody.getTrackingNumber());
                throw new ProviderException();
            }
        } catch (ConnectionException | TimeoutException e) {
            throw new ProviderException();
        } catch (RestClientErrorException e) {
            logger.error("Invalid response for inquiry {}", e.getResponseBody());
            throw new ProviderException();
        }
    }
}
