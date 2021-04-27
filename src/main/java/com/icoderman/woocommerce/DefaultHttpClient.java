package com.icoderman.woocommerce;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.*;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DefaultHttpClient implements HttpClient {

    private static final String CONTENT_TYPE = "Content-Type";
    private static final String APPLICATION_JSON = "application/json";

    private CloseableHttpClient httpClient;
    private ObjectMapper mapper;

    public DefaultHttpClient() {
        this.httpClient = HttpClientBuilder.create().build();
        this.mapper = new ObjectMapper();
    }

    @Override
    public Map get(String url) {
        HttpGet httpGet = new HttpGet(url);
        return getEntityAndReleaseConnection(httpGet, Map.class);
    }

    @Override
    public List getAll(String url) {
        HttpGet httpGet = new HttpGet(url);
        return getEntityAndReleaseConnection(httpGet, List.class);
    }

    @Override
    public Map post(String url, Map<String, String> params, Map<String, Object> object) {
        List<NameValuePair> postParameters = getParametersAsList(params);
        HttpPost httpPost;
        try {
            URIBuilder uriBuilder = new URIBuilder(url);
            uriBuilder.addParameters(postParameters);
            httpPost = new HttpPost(uriBuilder.build());
            httpPost.setHeader(CONTENT_TYPE, APPLICATION_JSON);
            return postEntity(object, httpPost);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Map put(String url, Map<String, String> params, Map<String, Object> object) {
        List<NameValuePair> postParameters = getParametersAsList(params);
        HttpPut httpPut;
        try {
            URIBuilder uriBuilder = new URIBuilder(url);
            uriBuilder.addParameters(postParameters);
            httpPut = new HttpPut(uriBuilder.build());
            httpPut.setHeader(CONTENT_TYPE, APPLICATION_JSON);
            return postEntity(object, httpPut);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Map delete(String url, Map<String, String> params) {
        List<NameValuePair> postParameters = getParametersAsList(params);
        HttpDelete httpDelete;
        try {
            URIBuilder uriBuilder = new URIBuilder(url);
            uriBuilder.addParameters(postParameters);
            httpDelete = new HttpDelete(uriBuilder.build());
            return getEntityAndReleaseConnection(httpDelete, Map.class);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    private Map postEntity(Map<String, Object> objectForJson, HttpEntityEnclosingRequestBase httpPost) {
        Gson gson = new Gson();
        System.out.println(gson.toJson(objectForJson));
        HttpEntity entity = new StringEntity(gson.toJson(objectForJson), ContentType.APPLICATION_JSON);
        httpPost.setEntity(entity);
        return getEntityAndReleaseConnection(httpPost, Map.class);
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

    private <T> T getEntityAndReleaseConnection(HttpRequestBase httpRequest, Class<T> objectClass) {
        try {
            HttpResponse httpResponse = httpClient.execute(httpRequest);
            HttpEntity httpEntity = httpResponse.getEntity();

            if (httpEntity == null) {
                throw new RuntimeException("Error retrieving results from http request");
            }
            Object result = mapper.readValue(httpEntity.getContent(), Object.class);
            if (objectClass.isInstance(result)) {
                return objectClass.cast(result);
            }
            throw new RuntimeException("Can't parse retrieved object: " + result.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            httpRequest.releaseConnection();
        }
    }

    public String InputToString(InputStream in) throws IOException {
        try {
            StringBuilder stringBuilder = new StringBuilder();
            Reader reader = new InputStreamReader(in);
            int data = reader.read();


            while (data != -1) {
                stringBuilder.append((char) data);
                data = reader.read();
            }
            return stringBuilder.toString();
        } catch (IOException ex) {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            throw ex;
        }
    }
}
