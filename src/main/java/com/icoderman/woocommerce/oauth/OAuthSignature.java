package com.icoderman.woocommerce.oauth;

import java.util.Map;
import java.util.Optional;

/**
 * OAuth Signature class, containing calculated OAuth properties.
 * Results to be used can be obtained in Header or Query formats.
 * 
 * Optional values are retrieved as java.util.Optional.
 */
public interface OAuthSignature {

	String getAsHeader();
	
	String getAsQueryString();

	Map<String, String> getAsMap();
	
	String getSignature();

	String getConsumerKey();

	String getTimestamp();

	String getNonce();

	String getSignatureMethod();

	String getVersion();

	Optional<String> getScope();

	Optional<String> getCallback();

	Optional<String> getToken();

	Optional<String> getVerifier();
	
	Optional<String> getRealm();
}
