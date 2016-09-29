package com.icoderman.woocommerce.oauth.core.signature;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

class Base64Encoder {

    private static Base64Encoder instance;

    public static Base64Encoder getInstance() {
        synchronized (Base64Encoder.class) {
            if (instance == null) {
                instance = new Base64Encoder();
            }
        }
        return instance;
    }
    
    public String encode(byte[] bytes) {
        try {
            return new String(Base64.getEncoder().encode(bytes), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new IllegalStateException("Can't perform base64 encoding", e);
        }
    }
}