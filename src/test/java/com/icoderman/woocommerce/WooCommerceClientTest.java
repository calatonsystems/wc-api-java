package com.icoderman.woocommerce;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

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
    public void apiGetAllProductsTest() {
        Object products = wooCommerce.getAll(WooCommerceEntity.PRODUCTS_CATEGORIES);
        Assert.assertNotNull(products);
    }
}
