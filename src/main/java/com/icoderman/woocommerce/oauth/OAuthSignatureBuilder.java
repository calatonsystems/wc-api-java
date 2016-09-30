package com.icoderman.woocommerce.oauth;

import com.icoderman.woocommerce.oauth.core.OAuthSignatureGenerator;

import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

/**
 * OAuthSignature Builder class.
 * Helps creating new unmutable instances of the OAuthSignature class.
 */
public class OAuthSignatureBuilder {

    private final OAuthSignatureGenerator signatureGenerator;

    private HttpMethod httpMethod;
    private String requestUrl;
    private Set<HttpParameter> queryParams;
    private Set<HttpParameter> bodyParams;

    public OAuthSignatureBuilder(OAuthConfig oAuthConfig, HttpMethod requestMethod, String requestUrl) {

        Objects.requireNonNull(oAuthConfig, "OAuthConfig cannot be null");
        Objects.requireNonNull(requestMethod, "HttpMethod cannot be null");
        Objects.requireNonNull(requestUrl, "RequestUrl cannot be null");

        this.signatureGenerator = new OAuthSignatureGenerator(oAuthConfig);

        this.httpMethod = requestMethod;
        this.requestUrl = extractQueryParamsFromUrl(requestUrl);
    }

    public OAuthSignatureBuilder addQueryParam(String key, String value) {

        Objects.requireNonNull(key, "Key cannot be null");
        Objects.requireNonNull(value, "Value cannot be null");

        if (queryParams == null) {
            queryParams = new HashSet<>();
        }

        queryParams.add(new HttpParameter(key, value));

        return this;
    }

    public OAuthSignatureBuilder addFormUrlEncodedParam(String key, String value) {

        Objects.requireNonNull(key, "Key cannot be null");
        Objects.requireNonNull(value, "Value cannot be null");

        if (bodyParams == null) {
            bodyParams = new HashSet<>();
        }

        bodyParams.add(new HttpParameter(key, value));

        return this;
    }

    public OAuthSignatureBuilder setQueryParams(Set<HttpParameter> queryParams) {

        Objects.requireNonNull(queryParams, "QueryParams cannot be null");
        this.queryParams = queryParams;

        return this;
    }

    public OAuthSignatureBuilder setFormUrlEncodedParams(Set<HttpParameter> formUrlEncodedParams) {

        Objects.requireNonNull(formUrlEncodedParams, "BodyParams cannot be null");
        this.bodyParams = formUrlEncodedParams;

        return this;
    }

    public OAuthSignature create() {

        Optional<Set<HttpParameter>> optionalQueryParams = Optional.ofNullable(queryParams);
        Optional<Set<HttpParameter>> optionalBodyParams = Optional.ofNullable(bodyParams);

        OAuthSignature signature = signatureGenerator.getSignature(httpMethod, requestUrl, optionalQueryParams, optionalBodyParams);

        return signature;
    }

    private String extractQueryParamsFromUrl(String requestUrl) {

        String normalizedUrl = Optional.of(requestUrl)
                .map(url -> url.split("\\?"))
                .filter(urlSplit -> urlSplit.length > 1)
                .map(urlSplit -> {

                    addUrlQueryParams(urlSplit[1]);

                    return requestUrl.replaceAll("\\?.*", "");
                })
                .orElse(requestUrl);

        return normalizedUrl;
    }

    private void addUrlQueryParams(String urlQueryParams) {

        for (String queryParam : urlQueryParams.split("&")) {

            String[] paramSplit = queryParam.split("=");
            String paramKey = paramSplit[0];
            String paramValue = Optional.of(paramSplit)
                    .filter(split -> split.length > 1)
                    .map(split -> split[1])
                    .orElse("");

            addQueryParam(paramKey, paramValue);
        }
    }
}
