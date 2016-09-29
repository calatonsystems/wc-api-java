package com.icoderman.woocommerce;

public final class WooCommerceConfig {

    private final String url;
    private final String consumerKey;
    private final String consumerSecret;

    public WooCommerceConfig(String url, String consumerKey, String consumerSecret) {
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
