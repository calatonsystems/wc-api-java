package com.icoderman.woocommerce.integration;


import com.icoderman.woocommerce.WooCommerce;
import com.icoderman.woocommerce.WooCommerceAPI;
import com.icoderman.woocommerce.WooCommerceConfig;
import com.icoderman.woocommerce.WooCommerceEntity;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
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

    @Ignore
    @Test
    public void apiCreateProductTest() {
        Map<String, Object> productInfo = new HashMap<>();
        productInfo.put("name", "Premium Quality");
        productInfo.put("type", "simple");
        productInfo.put("regular_price", "21.99");
        productInfo.put("description", "Pellentesque habitant morbi tristique senectus et netus");
        Map product = wooCommerce.create(WooCommerceEntity.PRODUCTS.getPath(), productInfo);
        Assert.assertNotNull(product);
    }

    @Ignore
    @Test
    public void apiGetAllProductsTest() {
        Object products = wooCommerce.getAll(WooCommerceEntity.PRODUCTS.getPath());
        Assert.assertNotNull(products);
    }

    @Ignore
    @Test
    public void apiGetProductTest() {
        Map product = wooCommerce.get(WooCommerceEntity.PRODUCTS.getPath(), 10);
        Assert.assertNotNull(product);
    }

    @Ignore
    @Test
    public void apiUpdateProductTest() {
        Map<String, Object> productInfo = new HashMap<>();
        productInfo.put("name", "Premium Quality UPDATED");
        Object product = wooCommerce.update(WooCommerceEntity.PRODUCTS.getPath(), 10, productInfo);
        Assert.assertNotNull(product);
    }

    @Ignore
    @Test
    public void apiDeleteProductTest() {
        Object product = wooCommerce.delete(WooCommerceEntity.PRODUCTS.getPath(), 10);
        Assert.assertNotNull(product);
    }
}
