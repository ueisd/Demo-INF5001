package com.sirra.demo.model;

public class UrlConfig {
    private String serverHost;
    private int  serverPort;

    public UrlConfig(String serverHost, int serverPort) {
        this.serverPort = serverPort;
        this.serverHost = serverHost;
    }
}
