package com.icoderman.woocommerce;

import java.util.Map;

/**
 * Basic interface for HTTP client
 */
public interface HttpClient {

    /**
     * Requests url with HTTP GET and returns result object as Map
     *
     * @param url url to request
     * @return retrieved result
     */
    Map get(String url);

    /**
     * Requests url with HTTP GET and returns List of objects (Maps)
     *
     * @param url url to request
     * @return retrieved result
     */
    Page getAll(String url);

    /**
     * Requests url with HTTP POST and retrieves result object as Map
     *
     * @param url    url to request
     * @param params request params
     * @param object request object with will be sent as json
     * @return retrieved result
     */
    Map post(String url, Map<String, String> params, Map<String, Object> object);

    /**
     * Requests url with HTTP PUT and retrieves result object as Map
     *
     * @param url    url to request
     * @param params request params
     * @param object request object with will be sent as json
     * @return retrieved result
     */
    Map put(String url, Map<String, String> params, Map<String, Object> object);

    /**
     * Requests url with HTTP DELETE and retrieves result object as Map
     *
     * @param url    url to request
     * @param params request params
     * @return retrieved result
     */
    Map delete(String url, Map<String, String> params);
}
