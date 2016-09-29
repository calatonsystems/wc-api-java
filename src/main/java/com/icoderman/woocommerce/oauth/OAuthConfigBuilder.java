package com.icoderman.woocommerce.oauth;

import com.icoderman.woocommerce.oauth.core.DefaultTimestampNonceFactory;
import com.icoderman.woocommerce.oauth.core.signature.HMACSha1SignatureMethod;

import java.util.Objects;
import java.util.Optional;

/**
 * OAuthConfig Builder class. 
 * Helps creating an unmutable instance OAuthConfig.
 */
public class OAuthConfigBuilder {

	private static final String CALLBACK_OUT_OF_BAND = "oob";

	private SignatureMethod signatureMethod = new HMACSha1SignatureMethod();
	private TimestampNonceFactory timestampNonceFactory = new DefaultTimestampNonceFactory();

	private final String consumerKey;
	private final String consumerSecret;
	
// nullable fields:
	private String tokenKey;
	private String tokenSecret;

	private String scope;
	private String callback;
	private String verifier;
	private String realm;

	public OAuthConfigBuilder(String consumerKey, String consumerSecret) {

		Objects.requireNonNull(consumerKey, "ConsumerKey cannot be null");
		Objects.requireNonNull(consumerSecret, "ConsumerSecret cannot be null");

		this.consumerKey = consumerKey;
		this.consumerSecret = consumerSecret;
	}

	public OAuthConfigBuilder withTimestampNonceFactory(TimestampNonceFactory timestampNonceFactory) {

		Objects.requireNonNull(timestampNonceFactory, "TimestampNonceFactory cannot be null");
		this.timestampNonceFactory = timestampNonceFactory;

		return this;
	}

	public OAuthConfigBuilder withSignatureMethod(SignatureMethod signatureMethod) {

		Objects.requireNonNull(signatureMethod, "SignatureMethod cannot be null");
		this.signatureMethod = signatureMethod;

		return this;
	}

	public OAuthConfigBuilder withDefaultCallback() {

		this.callback = CALLBACK_OUT_OF_BAND;

		return this;
	}

	public OAuthConfigBuilder setTokenKeys(String tokenKey, String tokenSecret) {

		if ((tokenKey != null && tokenSecret == null) || (tokenKey == null && tokenSecret != null)) {
			throw new IllegalArgumentException("Token keys must be both null or not null");
		}

		this.tokenKey = tokenKey;
		this.tokenSecret = tokenSecret;

		return this;
	}

	public OAuthConfigBuilder setScope(String scope) {

		this.scope = scope;

		return this;
	}

	public OAuthConfigBuilder setCallback(String callback) {

		this.callback = callback;

		return this;
	}

	public OAuthConfigBuilder setVerifier(String verifier) {

		this.verifier = verifier;

		return this;
	}
	
	public OAuthConfigBuilder setRealm(String realm) {

		this.realm = realm;

		return this;
	}
	public OAuthConfig build() {
		return new OAuthConfigDto(this);
	}
	
	private static class OAuthConfigDto implements OAuthConfig {

	    private final String consumerKey;
	    private final String consumerSecret;
	    
	    private final TimestampNonceFactory timestampNonceFactory;
	    private final SignatureMethod signatureMethod;
	    
	    private final Optional<String> tokenKey;
	    private final Optional<String> tokenSecret;

	    private final Optional<String> scope;
	    private final Optional<String> callback;
	    private final Optional<String> verifier;
	    private final Optional<String> realm;

	    public OAuthConfigDto(OAuthConfigBuilder builder) {

	        this.consumerKey = builder.consumerKey;
	        this.consumerSecret = builder.consumerSecret;
	        this.signatureMethod = builder.signatureMethod;
	        this.timestampNonceFactory = builder.timestampNonceFactory;
	        
	        this.tokenKey = Optional.ofNullable(builder.tokenKey);
	        this.tokenSecret = Optional.ofNullable(builder.tokenSecret);
	        this.scope = Optional.ofNullable(builder.scope);
	        this.callback = Optional.ofNullable(builder.callback);
	        this.verifier = Optional.ofNullable(builder.verifier);
	        this.realm = Optional.ofNullable(builder.realm);
	    }

	    @Override
	    public String getConsumerKey() {
	        return consumerKey;
	    }

	    @Override
	    public TimestampNonceFactory getTimestampNonceFactory() {
	        return timestampNonceFactory;
	    }

	    @Override
	    public SignatureMethod getSignatureMethod() {
	        return signatureMethod;
	    }

	    @Override
	    public Optional<String> getTokenKey() {
	        return tokenKey;
	    }

	    @Override
	    public Optional<String> getScope() {
	        return scope;
	    }

	    @Override
	    public Optional<String> getCallback() {
	        return callback;
	    }

	    @Override
	    public Optional<String> getVerifier() {
	        return verifier;
	    }
	    
	    @Override
	    public String getConsumerSecret() {
	        return consumerSecret;
	    }
	    
	    @Override
	    public Optional<String> getTokenSecret() {
	        return tokenSecret;
	    }

		@Override
		public Optional<String> getRealm() {
			return realm;
		}
	}
}