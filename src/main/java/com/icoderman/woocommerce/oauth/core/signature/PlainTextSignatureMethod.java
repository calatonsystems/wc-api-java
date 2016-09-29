package com.icoderman.woocommerce.oauth.core.signature;

import com.icoderman.woocommerce.oauth.SignatureMethod;
import com.icoderman.woocommerce.oauth.SignatureMethodType;

/**
 * Plaintext implementation of {@link SignatureMethod}.
 */
public class PlainTextSignatureMethod implements SignatureMethod {

    @Override
    public String getSignature(String baseString, String apiSecret, String tokenSecret) {

    	try {
            return apiSecret + '&' + tokenSecret;

    	} catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public SignatureMethodType getSignatureMethodType() {
        return SignatureMethodType.PLAINTEXT;
    }
}