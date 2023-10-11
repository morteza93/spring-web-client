package com.example.springwebclient.provider;

import com.example.springwebclient.common.ExternalServiceConfig;
import com.tosantechno.common.config.ExternalServiceConfig;
import com.tosantechno.common.handler.LoggingRequestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Set;

@Component
public class RestClientFactory {

    @Value("${security.mask.enable:true}")
    private Boolean maskDto;
    @Value("${security.mask.fields:}")
    private Set<String> maskFields;

    public RestClient restClient(ExternalServiceConfig serviceConfig){
        LoggingRequestInterceptor loggingRequestInterceptor = new LoggingRequestInterceptor(maskDto, maskFields);
        RestTemplate restTemplate = restTemplate(serviceConfig, loggingRequestInterceptor);
        return new GeneralRestClient(serviceConfig, restTemplate);
    }

    private RestTemplate restTemplate(ExternalServiceConfig serviceConfig, LoggingRequestInterceptor loggingRequestInterceptor){
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getInterceptors().add(loggingRequestInterceptor);
        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();

        Integer connectTimeout = serviceConfig.getConnectTimeout();
        if (connectTimeout != null) {
            requestFactory.setConnectTimeout(connectTimeout);
        }

        Integer readTimeout = serviceConfig.getReadTimeout();
        if (readTimeout != null) {
            requestFactory.setReadTimeout(readTimeout);
        }

        BufferingClientHttpRequestFactory bufferingClientHttpRequestFactory = new BufferingClientHttpRequestFactory(requestFactory);
        restTemplate.setRequestFactory(bufferingClientHttpRequestFactory);
        return restTemplate;
    }
}
