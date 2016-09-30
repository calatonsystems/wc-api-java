package com.icoderman.woocommerce.oauth;

import java.util.Optional;

/**
 * OAuth Config class.
 * Contains OAuth related information, used later to create many new requests signatures.
 *
 * Optional parameters are returned as java.util.Optional type.
 */
public interface OAuthConfig {

    TimestampNonceFactory getTimestampNonceFactory();

    SignatureMethod getSignatureMethod();


    String getConsumerKey();

    String getConsumerSecret();


    Optional<String> getTokenKey();

    Optional<String> getTokenSecret();


    Optional<String> getCallback();

    Optional<String> getVerifier();

    Optional<String> getScope();

    Optional<String> getRealm();


    default OAuthSignatureBuilder buildSignature(HttpMethod method, String url) {
        return new OAuthSignatureBuilder(this, method, url);
    }
}