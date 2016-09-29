package com.icoderman.woocommerce;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
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

public class DefaultWooCommerceHttpClient implements WooCommerceHttpClient {

    private CloseableHttpClient httpClient;
    private ObjectMapper mapper;

    public DefaultWooCommerceHttpClient() {
        this.httpClient = HttpClientBuilder.create().build();
        this.mapper = new ObjectMapper();
    }

    public Object get(String url) {
        HttpGet httpGet = new HttpGet(url);
        try {
            HttpResponse httpResponse = this.httpClient.execute(httpGet);

            HttpEntity httpEntity = httpResponse.getEntity();
            if (httpEntity == null) {
                throw new NullPointerException();
            } else {
                return this.mapper.readValue(httpEntity.getContent(), Object.class);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            httpGet.releaseConnection();
        }
    }

    public Object post(String url, Map<String, String> params, Object object) {
        List<NameValuePair> postParameters = new ArrayList<>();
        if (params != null && params.size() > 0) {
            for (String key : params.keySet()) {
                postParameters.add(new BasicNameValuePair(key, params.get(key)));
            }
        }
        HttpPost httpPost;
        try {
            URIBuilder uriBuilder = new URIBuilder(url);
            uriBuilder.addParameters(postParameters);
            httpPost = new HttpPost(uriBuilder.build());
            httpPost.setHeader("Content-Type", "application/json");
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

        try {
            HttpEntity entity = new ByteArrayEntity(this.mapper.writeValueAsBytes(object), ContentType.APPLICATION_JSON);
            httpPost.setEntity(entity);

            return getEntityAndReleaseConnection(httpPost);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }

    private Object getEntityAndReleaseConnection(HttpRequestBase httpRequest) {
        try {
            HttpResponse httpResponse = this.httpClient.execute(httpRequest);

            HttpEntity httpEntity = httpResponse.getEntity();
            if (httpEntity == null) {
                throw new NullPointerException();
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
