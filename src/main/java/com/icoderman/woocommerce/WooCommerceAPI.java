package com.icoderman.woocommerce;

import com.icoderman.woocommerce.oauth.OAuthSignature;
import com.icoderman.woocommerce.oauth.OAuthConfig;

import java.util.List;
import java.util.Map;

public class WooCommerceAPI implements WooCommerce {

    private static final String API_URL_FORMAT = "%s/wp-json/wc/%s/%s";
    private static final String API_URL_ONE_ENTITY_FORMAT = "%s/wp-json/wc/%s/%s/%d";
    private static final String URL_SECURED_FORMAT = "%s?%s";

    private HttpClient client;
    private OAuthConfig config;
    private String apiVersion;

    public WooCommerceAPI(OAuthConfig config, ApiVersionType apiVersion) {
        this.config = config;
        this.client = new DefaultHttpClient(config.getSslTrusted());
        this.apiVersion = apiVersion.getValue();
    }

    @Override
    public Map create(String endpointBase, Map<String, Object> object) {
        String url = String.format(API_URL_FORMAT, config.getUrl(), apiVersion, endpointBase);
        return client.post(url, OAuthSignature.getAsMap(config, url, HttpMethod.POST), object);
    }

    @Override
    public Map get(String endpointBase, int id) {
        String url = String.format(API_URL_ONE_ENTITY_FORMAT, config.getUrl(), apiVersion, endpointBase, id);
        String signature = OAuthSignature.getAsQueryString(config, url, HttpMethod.GET);
        String securedUrl = String.format(URL_SECURED_FORMAT, url, signature);
        return client.get(securedUrl);
    }

    @Override
    public List getAll(String endpointBase, Map<String, String> params) {
        String url = String.format(API_URL_FORMAT, config.getUrl(), apiVersion, endpointBase);
        String signature = OAuthSignature.getAsQueryString(config, url, HttpMethod.GET, params);
        String securedUrl = String.format(URL_SECURED_FORMAT, url, signature);
        return client.getAll(securedUrl);
    }

    @Override
    public Map update(String endpointBase, int id, Map<String, Object> object) {
        String url = String.format(API_URL_ONE_ENTITY_FORMAT, config.getUrl(), apiVersion, endpointBase, id);
        return client.put(url, OAuthSignature.getAsMap(config, url, HttpMethod.PUT), object);
    }

    @Override
    public Map delete(String endpointBase, int id) {
        String url = String.format(API_URL_ONE_ENTITY_FORMAT, config.getUrl(), apiVersion, endpointBase, id);
        Map<String, String> params = OAuthSignature.getAsMap(config, url, HttpMethod.DELETE);
        return client.delete(url, params);
    }
}
