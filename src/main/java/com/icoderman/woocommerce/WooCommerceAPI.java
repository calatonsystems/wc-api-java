package com.icoderman.woocommerce;

import com.icoderman.woocommerce.oauth.HttpMethod;
import com.icoderman.woocommerce.oauth.OAuthConfig;
import com.icoderman.woocommerce.oauth.OAuthConfigBuilder;
import com.icoderman.woocommerce.oauth.OAuthSignature;

public class WooCommerceAPI implements WooCommerce {

    private static final String API_GET_ALL_URL_FORMAT = "%s/wp-json/wc/v1/%s";
    private static final String API_GET_ONE_URL_FORMAT = "%s/wp-json/wc/v1/%s/%d";
    private static final String API_GET_SECURED_URL_FORMAT = "%s?%s";

    private HttpClient client;
    private WooCommerceConfig config;
    private OAuthConfig oauthConfig;

    public WooCommerceAPI(WooCommerceConfig config) {
        this.config = config;
        this.client = new DefaultHttpClient();
        this.oauthConfig = new OAuthConfigBuilder(config.getConsumerKey(), config.getConsumerSecret()).build();
    }


    @Override
    public Object create(WooCommerceEntity entity, Object object) {
        return null;
    }

    @Override
    public Object get(WooCommerceEntity entity, int id) {
        String url = String.format(API_GET_ONE_URL_FORMAT, config.getUrl(), entity.getSlug(), id);
        return getObject(url);
    }

    @Override
    public Object getAll(WooCommerceEntity entity) {
        String url = String.format(API_GET_ALL_URL_FORMAT, config.getUrl(), entity.getSlug());
        return getObject(url);
    }

    private Object getObject(String url) {
        OAuthSignature signature = getSignature(url, HttpMethod.GET);
        String securedUrl = String.format(API_GET_SECURED_URL_FORMAT, url, signature.getAsQueryString());
        return client.get(securedUrl);
    }

    @Override
    public Object update(WooCommerceEntity entity, int id, Object object) {
        return null;
    }

    @Override
    public Object delete(WooCommerceEntity entity, int id) {
        return null;
    }

    private OAuthSignature getSignature(String url, HttpMethod httpMethod) {
        return this.oauthConfig.buildSignature(httpMethod, url).create();
    }
}
