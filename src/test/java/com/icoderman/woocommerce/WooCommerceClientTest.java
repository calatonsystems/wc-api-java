package com.icoderman.woocommerce;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class WooCommerceClientTest {

    private static final String CONSUMER_KEY = "ck_700a11f6fa9fb498a58dd3c252d8e52e93f3f073";
    private static final String CONSUMER_SECRET = "cs_a2117031fd9283f995e9822f6a739e65486a0528";
    private static final String WC_URL = "http://localhost:32789";

    private WooCommerce wooCommerce;

    @Before
    public void setUp() {
        this.wooCommerce = new WooCommerceAPI(new WooCommerceConfig(WC_URL, CONSUMER_KEY, CONSUMER_SECRET));
    }

    @Test
    public void apiCreateProductTest() {
        Map<String, String> productInfo = new HashMap<>();
        productInfo.put("name", "Premium Quality");
        productInfo.put("type", "simple");
        productInfo.put("regular_price", "21.99");
        productInfo.put("description", "Pellentesque habitant morbi tristique senectus et netus");
        Object product = wooCommerce.create(WooCommerceEntity.PRODUCTS.getValue(), productInfo);
        Assert.assertNotNull(product);
    }

    @Test
    public void apiGetAllProductsTest() {
        Object products = wooCommerce.getAll(WooCommerceEntity.PRODUCTS.getValue());
        Assert.assertNotNull(products);
    }

    @Test
    public void apiGetProductTest() {
        Object product = wooCommerce.get(WooCommerceEntity.PRODUCTS.getValue(), 10);
        Assert.assertNotNull(product);
    }

    @Test
    public void apiUpdateProductTest() {
        Map<String, String> productInfo = new HashMap<>();
        productInfo.put("name", "Premium Quality UPDATED");
        Object product = wooCommerce.update(WooCommerceEntity.PRODUCTS.getValue(), 10, productInfo);
        Assert.assertNotNull(product);
    }

    @Test
    public void apiDeleteProductTest() {
        Object product = wooCommerce.delete(WooCommerceEntity.PRODUCTS.getValue(), 10, false);
        Assert.assertNotNull(product);
    }
}
