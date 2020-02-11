package com.yobuligo.restaccess.api;

public class AuthorizationRequestConfig implements IAuthorizationRequestConfig {
    private String authEndpointUri;
    private String tokenEndpointUri;
    private String cliendId;

    public AuthorizationRequestConfig(String authEndpointUri, String tokenEndpointUri, String cliendId) {
        this.authEndpointUri = authEndpointUri;
        this.tokenEndpointUri = tokenEndpointUri;
        this.cliendId = cliendId;
    }

    @Override
    public String getAuthEndpointUri() {
        return authEndpointUri;
    }

    @Override
    public String getTokenEndpointUri() {
        return tokenEndpointUri;
    }

    @Override
    public String getClientId() {
        return cliendId;
    }
}