package com.icoderman.woocommerce.oauth;

/**
 * Enum of every OAuth field included in a signature.
 */
public enum OAuthField {

	TIMESTAMP("oauth_timestamp"),
	SIGNATURE_METHOD("oauth_signature_method"),
	SIGNATURE("oauth_signature"),
	CONSUMER_KEY("oauth_consumer_key"),
	VERSION("oauth_version"),
	NONCE("oauth_nonce"),
	TOKEN("oauth_token"),
	VERIFIER("oauth_verifier"),
	CALLBACK("oauth_callback"),
	SCOPE("scope"),
	REALM("realm");

	private OAuthField(String fieldName) {
		this.fieldName = fieldName;
	}

	private final String fieldName;

	public String fieldName() {
		return fieldName;
	}
}
