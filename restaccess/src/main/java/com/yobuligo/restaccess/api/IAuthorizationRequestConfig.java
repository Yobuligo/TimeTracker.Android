package com.yobuligo.restaccess.api;

/**
 * The authorization request configuration contains the endpoints to the ID Provider
 */
public interface IAuthorizationRequestConfig {

    /**
     * Returns the authorization endpoint uri of the ID Provider
     *
     * @return
     */
    String getAuthEndpointUri();

    /**
     * Returns the token endpoint uri of the ID Provider
     *
     * @return
     */
    String getTokenEndpointUri();

    /**
     * Returns the cliend ID of the ID Provider
     *
     * @return
     */
    String getClientId();
}