package com.icoderman.woocommerce;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.icoderman.woocommerce.oauth.HttpMethod;
import com.icoderman.woocommerce.oauth.OAuthConfig;
import com.icoderman.woocommerce.oauth.OAuthConfigBuilder;
import com.icoderman.woocommerce.oauth.OAuthSignature;

public class WooCommerceAPI implements WooCommerce {


    private WooCommerceHttpClient client;
    private WooCommerceConfig config;
    private OAuthConfig oauthConfig;

    public WooCommerceAPI(WooCommerceConfig config, WooCommerceHttpClient client) {
        this.config = config;
        this.client = client;
        this.oauthConfig = new OAuthConfigBuilder(config.getConsumerKey(), config.getConsumerSecret()).build();
    }


    @Override
    public Object create(WooCommerceEntity entity, Object object) {
        return null;
    }

    @Override
    public Object get(WooCommerceEntity entity, int id) {
        String url = config.getUrl() + "/wp-json/wc/v1" + entity.getSlug() + "/" + id;
        OAuthSignature signature = getSignature(url, HttpMethod.GET);
        return client.get(url + "?" + signature.getAsQueryString());
    }

    @Override
    public Object getAll(WooCommerceEntity entity) {
        String url = config.getUrl() + "/wp-json/wc/v1" + entity.getSlug();
        OAuthSignature signature = getSignature(url, HttpMethod.GET);
        return client.get(url + "?" + signature.getAsQueryString());
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
