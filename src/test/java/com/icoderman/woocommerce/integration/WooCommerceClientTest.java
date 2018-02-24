package com.icoderman.woocommerce.integration;


import com.icoderman.woocommerce.EndpointBaseType;
import com.icoderman.woocommerce.WooCommerce;
import com.icoderman.woocommerce.WooCommerceAPI;
import com.icoderman.woocommerce.oauth.OAuthConfig;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class WooCommerceClientTest {

    private static final String CONSUMER_KEY = "ck_d35e7be7cc695d87f23490729dd80e173f88c8f5";
    private static final String CONSUMER_SECRET = "cs_53a835760712ebf0c8bcf2a21197af4b2323a052";
    private static final String WC_URL = "http://localhost/index.php";

    private WooCommerce wooCommerce;

    @Before
    public void setUp() {
        wooCommerce = new WooCommerceAPI(new OAuthConfig(WC_URL, CONSUMER_KEY, CONSUMER_SECRET));
    }

    @Ignore
    @Test
    public void apiCreateProductTest() {
        Map<String, Object> productInfo = new HashMap<>();
        productInfo.put("name", "Premium Quality");
        productInfo.put("type", "simple");
        productInfo.put("regular_price", "21.99");
        productInfo.put("description", "Pellentesque habitant morbi tristique senectus et netus");
        Map product = wooCommerce.create(EndpointBaseType.PRODUCTS.getValue(), productInfo);
        Assert.assertNotNull(product);
    }

    @Ignore
    @Test
    public void apiGetAllProductsTest() {
        Map<String, String> params = new HashMap<>();
        params.put("per_page","100");
        params.put("offset","0");
        Object products = wooCommerce.getAll(EndpointBaseType.PRODUCTS.getValue(), params);
        Assert.assertNotNull(products);
    }

    @Ignore
    @Test
    public void apiGetProductTest() {
        Map product = wooCommerce.get(EndpointBaseType.PRODUCTS.getValue(), 79);
        Assert.assertNotNull(product);
    }

    @Ignore
    @Test
    public void apiUpdateProductTest() {
        Map<String, Object> productInfo = new HashMap<>();
        productInfo.put("name", "Premium Quality UPDATED");
        Map product = wooCommerce.update(EndpointBaseType.PRODUCTS.getValue(), 10, productInfo);
        Assert.assertNotNull(product);
    }

    @Ignore
    @Test
    public void apiDeleteProductTest() {
        Map product = wooCommerce.delete(EndpointBaseType.PRODUCTS.getValue(), 10);
        Assert.assertNotNull(product);
    }
}
