package com.icoderman.woocommerce;

import java.util.Map;

public interface WooCommerceHttpClient {

    Object get(String url);
    Object post(String url, Map<String, String> params, Object object);

}
