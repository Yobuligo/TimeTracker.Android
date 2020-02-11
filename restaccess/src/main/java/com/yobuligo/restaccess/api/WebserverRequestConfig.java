package com.yobuligo.restaccess.api;

public class WebserverRequestConfig implements IWebserviceRequestConfig {

    private String webserverUri;
    private String webserverPort;

    public WebserverRequestConfig(String webserverUri, String webserverPort) {
        this.webserverUri = webserverUri;
        this.webserverPort = webserverPort;
    }

    @Override
    public String getWebserverUri() {
        return webserverUri;
    }

    @Override
    public String getWebserverPort() {
        return webserverPort;
    }
}
