package com.example.springwebclient.common;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;

@Setter
@Getter
public class ExternalServiceConfig {

    private String marker;

    private SrvType serviceType;
    private AuthType authType;
    private URI baseUrl;

    private String token;

    private String username;
    private String password;
    private Integer connectTimeout;
    private Integer readTimeout;

    private HashMap<String, String> props;
    private HashMap<String, String> servicePaths;

    public URI getUrl(String pathName, String defaultPath) {
        if (servicePaths != null && servicePaths.containsKey(pathName)) {
            String s = servicePaths.get(pathName);
            return UriComponentsBuilder.fromUri(baseUrl).pathSegment(s).build().toUri();
        }
        if(defaultPath != null && !defaultPath.trim().isEmpty()){
            return UriComponentsBuilder.fromUri(baseUrl).pathSegment(defaultPath).build().toUri();
        }
        return null;
    }

    public String getProp(String name) {
        if (props != null) {
            return props.get(name);
        }
        return null;
    }

    enum SrvType {
        JPOS, REST, SOAP
    }

    public enum AuthType {
        FIX_TOKEN, TICKET, BASIC, OAUTH2
    }
}
