package com.icoderman.woocommerce;

import java.util.Map;

public interface HttpClient {

    Object get(String url);

    Object post(String url, Map<String, String> params, Map<String, Object> object);

    Object put(String url, Map<String, String> params, Map<String, Object> object);

    Object delete(String url, Map<String, String> params);
}
