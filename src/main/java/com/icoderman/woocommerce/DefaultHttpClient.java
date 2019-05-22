package com.icoderman.woocommerce;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.net.ssl.SSLContext;

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
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.ssl.TrustStrategy;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DefaultHttpClient implements HttpClient {

    private static final String CONTENT_TYPE = "Content-Type";
    private static final String APPLICATION_JSON = "application/json";

    private CloseableHttpClient httpClient;
    private ObjectMapper mapper;

    public DefaultHttpClient() {
    	super();
    	createDefaultHttpClient(true);
    }

	public DefaultHttpClient(Boolean sslTrusted) {
		super();
		createDefaultHttpClient(true);
 	}


    private void createDefaultHttpClient(Boolean sslTrusted) {
    	
       	SSLContext sslContext = getSslContext();

       	if (sslTrusted)
            this.httpClient = HttpClientBuilder.create().setSSLContext(sslContext)
    		.build();
       	else
	        this.httpClient = HttpClientBuilder.create().setSSLContext(sslContext)
	        		.build();
        
        this.mapper = new ObjectMapper();
	}

	private SSLContext getSslContext() {
		TrustStrategy acceptingTrustStrategy = new TrustStrategy() {
    	    public boolean isTrusted(X509Certificate[] certificate, String authType) {
    	        return true;
    	    }
    	};

    	SSLContext sslContext = null;
    	try {
    	    sslContext = SSLContexts.custom().loadTrustMaterial(null, acceptingTrustStrategy).build();
    	} catch (Exception e) {
    	    // Handle error
    	}
		return sslContext;
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
        try {
            HttpEntity entity = new ByteArrayEntity(this.mapper.writeValueAsBytes(objectForJson), ContentType.APPLICATION_JSON);
            httpPost.setEntity(entity);
            return getEntityAndReleaseConnection(httpPost, Map.class);
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
}
