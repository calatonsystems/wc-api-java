package com.icoderman.woocommerce.oauth;

public final class OAuthConfig {

    private final String url;
    private final String consumerKey;
    private final String consumerSecret;

    public OAuthConfig(String url, String consumerKey, String consumerSecret) {
        if (url == null || url.isEmpty() ||
                consumerKey == null || consumerKey.isEmpty() ||
                consumerSecret == null || consumerSecret.isEmpty()) {
            throw new IllegalArgumentException("All arguments are required");
        }
        this.url = url;
        this.consumerKey = consumerKey;
        this.consumerSecret = consumerSecret;
    }

    public String getUrl() {
        return url;
    }

    public String getConsumerKey() {
        return consumerKey;
    }

    public String getConsumerSecret() {
        return consumerSecret;
    }
}
