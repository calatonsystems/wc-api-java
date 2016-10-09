package com.icoderman.woocommerce;

import com.icoderman.woocommerce.oauth.HttpMethod;
import com.icoderman.woocommerce.oauth.OAuthConfig;
import com.icoderman.woocommerce.oauth.OAuthConfigBuilder;
import com.icoderman.woocommerce.oauth.OAuthSignature;

import java.util.List;
import java.util.Map;

public class WooCommerceAPI implements WooCommerce {

    private static final String API_URL_FORMAT = "%s/wp-json/wc/v1/%s";
    private static final String API_URL_ONE_ENTITY_FORMAT = "%s/wp-json/wc/v1/%s/%d";
    private static final String URL_SECURED_FORMAT = "%s?%s";
    private static final String DELETE_PARAM_FORCE = "force";

    private HttpClient client;
    private WooCommerceConfig config;
    private OAuthConfig oauthConfig;

    public WooCommerceAPI(WooCommerceConfig config) {
        this.config = config;
        this.client = new DefaultHttpClient();
        this.oauthConfig = new OAuthConfigBuilder(config.getConsumerKey(), config.getConsumerSecret()).build();
    }

    @Override
    public Map create(String endpointBase, Map<String, Object> object) {
        String url = String.format(API_URL_FORMAT, config.getUrl(), endpointBase);
        OAuthSignature signature = getSignature(url, HttpMethod.POST);
        return client.post(url, signature.getAsMap(), object);
    }

    @Override
    public Map get(String endpointBase, int id) {
        String url = String.format(API_URL_ONE_ENTITY_FORMAT, config.getUrl(), endpointBase, id);
        OAuthSignature signature = getSignature(url, HttpMethod.GET);
        String securedUrl = String.format(URL_SECURED_FORMAT, url, signature.getAsQueryString());
        return client.get(securedUrl);
    }

    @Override
    public List getAll(String endpointBase) {
        String url = String.format(API_URL_FORMAT, config.getUrl(), endpointBase);
        OAuthSignature signature = getSignature(url, HttpMethod.GET);
        String securedUrl = String.format(URL_SECURED_FORMAT, url, signature.getAsQueryString());
        return client.getAll(securedUrl);
    }

    @Override
    public Map update(String endpointBase, int id, Map<String, Object> object) {
        String url = String.format(API_URL_ONE_ENTITY_FORMAT, config.getUrl(), endpointBase, id);
        OAuthSignature signature = getSignature(url, HttpMethod.PUT);
        return client.put(url, signature.getAsMap(), object);
    }

    @Override
    public Map delete(String endpointBase, int id) {
        String url = String.format(API_URL_ONE_ENTITY_FORMAT, config.getUrl(), endpointBase, id);
        OAuthSignature signature = getSignature(url, HttpMethod.DELETE);
        Map<String, String> params = signature.getAsMap();
        params.put(DELETE_PARAM_FORCE, Boolean.TRUE.toString());
        return client.delete(url, signature.getAsMap());
    }

    private OAuthSignature getSignature(String url, HttpMethod httpMethod) {
        return oauthConfig.buildSignature(httpMethod, url).create();
    }
}
