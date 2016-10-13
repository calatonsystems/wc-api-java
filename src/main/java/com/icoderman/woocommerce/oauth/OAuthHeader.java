package com.icoderman.woocommerce.oauth;

/**
 * Enum with OAuth headers
 */
public enum OAuthHeader {

    OAUTH_CONSUMER_KEY("oauth_consumer_key"),
    OAUTH_TIMESTAMP("oauth_timestamp"),
    OAUTH_NONCE("oauth_nonce"),
    OAUTH_SIGNATURE_METHOD("oauth_signature_method"),
    OAUTH_SIGNATURE("oauth_signature");

    private String value;

    OAuthHeader(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
