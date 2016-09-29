package com.icoderman.woocommerce.oauth.core.signature;

import com.icoderman.woocommerce.oauth.SignatureMethod;
import com.icoderman.woocommerce.oauth.SignatureMethodType;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/**
 * HMAC-SHA1 implementation of {@link SignatureMethod}
 */
public class HMACSha1SignatureMethod implements SignatureMethod {

	private static final String EMPTY_STRING = "";
	private static final String CARRIAGE_RETURN = "\r\n";
	private static final String UTF8 = "UTF-8";
	private static final String HMAC_SHA1 = "HmacSHA1";

	@Override
	public String getSignature(String baseString, String apiSecret, String tokenSecret) {

		String keyString = apiSecret + '&' + tokenSecret;

		try {
			SecretKeySpec key = new SecretKeySpec(keyString.getBytes(UTF8), HMAC_SHA1);
			Mac mac = Mac.getInstance(HMAC_SHA1);
			mac.init(key);
			byte[] bytes = mac.doFinal(baseString.getBytes(UTF8));

			return bytesToBase64String(bytes).replace(CARRIAGE_RETURN, EMPTY_STRING);

		} catch (Exception e) {
			throw new IllegalStateException(e);
		}
	}

	@Override
	public SignatureMethodType getSignatureMethodType() {
		return SignatureMethodType.HMAC_SHA1;
	}

	private String bytesToBase64String(byte[] bytes) {
		return Base64Encoder.getInstance().encode(bytes);
	}
}