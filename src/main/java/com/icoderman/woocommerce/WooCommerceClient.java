package com.icoderman.woocommerce;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.util.List;

public class WooCommerceClient implements WooCommerce {

    private String url;
    private String consumerKey;
    private String consumerSecret;

    private CloseableHttpClient httpClient;
    private ObjectMapper mapper;

    public WooCommerceClient(String url, String consumerKey, String consumerSecret) {
        this.url = url;
        this.consumerKey = consumerKey;
        this.consumerSecret = consumerSecret;

        this.httpClient = HttpClientBuilder.create().build();
        this.mapper = new ObjectMapper();
    }


    @Override
    public Object create(WooCommerceEntity entity, Object object) {
        return null;
    }

    @Override
    public Object get(WooCommerceEntity entity, int id) {
        return null;
    }

    @Override
    public List getAll(WooCommerceEntity entity) {
        return null;
    }

    @Override
    public Object update(WooCommerceEntity entity, int id, Object object) {
        return null;
    }

    @Override
    public Object delete(WooCommerceEntity entity, int id) {
        return null;
    }
}
