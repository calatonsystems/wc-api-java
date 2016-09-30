package com.icoderman.woocommerce;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DefaultHttpClient implements HttpClient {

    private CloseableHttpClient httpClient;
    private ObjectMapper mapper;

    public DefaultHttpClient() {
        this.httpClient = HttpClientBuilder.create().build();
        this.mapper = new ObjectMapper();
    }

    @Override
    public Object get(String url) {
        HttpGet httpGet = new HttpGet(url);
        return getEntityAndReleaseConnection(httpGet);
    }

    @Override
    public Object post(String url, Map<String, String> params, Object object) {
        List<NameValuePair> postParameters = getParametersAsList(params);
        HttpPost httpPost;
        try {
            URIBuilder uriBuilder = new URIBuilder(url);
            uriBuilder.addParameters(postParameters);
            httpPost = new HttpPost(uriBuilder.build());
            httpPost.setHeader("Content-Type", "application/json");
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

        return postEntity(object, httpPost);
    }

    @Override
    public Object put(String url, Map<String, String> params, Object object) {
        List<NameValuePair> postParameters = getParametersAsList(params);
        HttpPut httpPut;
        try {
            URIBuilder uriBuilder = new URIBuilder(url);
            uriBuilder.addParameters(postParameters);
            httpPut = new HttpPut(uriBuilder.build());
            httpPut.setHeader("Content-Type", "application/json");
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

        return postEntity(object, httpPut);
    }

    @Override
    public Object delete(String url, Map<String, String> params) {
        List<NameValuePair> postParameters = getParametersAsList(params);
        HttpDelete httpDelete;
        try {
            URIBuilder uriBuilder = new URIBuilder(url);
            uriBuilder.addParameters(postParameters);
            httpDelete = new HttpDelete(uriBuilder.build());
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        return getEntityAndReleaseConnection(httpDelete);
    }

    private Object postEntity(Object objectForJson, HttpEntityEnclosingRequestBase httpPost) {
        try {
            HttpEntity entity = new ByteArrayEntity(this.mapper.writeValueAsBytes(objectForJson), ContentType.APPLICATION_JSON);
            httpPost.setEntity(entity);

            return getEntityAndReleaseConnection(httpPost);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private List<NameValuePair> getParametersAsList(Map<String, String> params) {
        List<NameValuePair> postParameters = new ArrayList<>();
        if (params != null && params.size() > 0) {
            for (String key : params.keySet()) {
                postParameters.add(new BasicNameValuePair(key, params.get(key)));
            }
        }
        return postParameters;
    }

    private Object getEntityAndReleaseConnection(HttpRequestBase httpRequest) {
        try {
            HttpResponse httpResponse = this.httpClient.execute(httpRequest);

            HttpEntity httpEntity = httpResponse.getEntity();
            if (httpEntity == null) {
                throw new RuntimeException("Error retrieving results from http request");
            } else {
                return this.mapper.readValue(httpEntity.getContent(), Object.class);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            httpRequest.releaseConnection();
        }
    }
}
