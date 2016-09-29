package com.icoderman.woocommerce.oauth.core.signature;

import com.icoderman.woocommerce.oauth.SignatureMethod;
import com.icoderman.woocommerce.oauth.SignatureMethodType;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.SignatureException;

/**
 * A signature service that uses the RSA-SHA1 algorithm.
 */
public class RSASha1SignatureMethod implements SignatureMethod {

    private static final String RSA_SHA1 = "SHA1withRSA";
    private static final String UTF8 = "UTF-8";

    private final PrivateKey privateKey;

    public RSASha1SignatureMethod(PrivateKey privateKey) {
        this.privateKey = privateKey;
    }

    @Override
    public String getSignature(String baseString, String apiSecret, String tokenSecret) {
        try {
            final Signature signature = Signature.getInstance(RSA_SHA1);
            signature.initSign(privateKey);
            signature.update(baseString.getBytes(UTF8));
            return bytesToBase64String(signature);
        } catch (NoSuchAlgorithmException | InvalidKeyException | SignatureException | UnsupportedEncodingException |
                RuntimeException e) {
            throw new IllegalStateException(baseString, e);
        }
    }

    @Override
    public SignatureMethodType getSignatureMethodType() {
        return SignatureMethodType.RSA_SHA1;
    }    

    private String bytesToBase64String(Signature signature) throws SignatureException {
        return Base64Encoder.getInstance().encode(signature.sign());
    }
}
