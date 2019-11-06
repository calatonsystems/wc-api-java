# WooCommerce API Java Wrapper
[![Build Status](https://travis-ci.org/icoderman/wc-api-java.svg?branch=master)](https://travis-ci.org/icoderman/wc-api-java)

Java wrapper for WooCommerce REST API. The library supports the latest versions of WooCommerce REST API only
with the OAuth 1.0a authentication over the HTTP protocol.

## Setup
wc-api-java is available on maven central:
```xml
    <dependency>
        <groupId>com.icoderman</groupId>
        <artifactId>wc-api-java</artifactId>
        <version>1.4</version>
    </dependency>
```

## Usage

```java
    public static void main(String[] args) {
        // Setup client
        OAuthConfig config = new OAuthConfig("http://woocommerce.com", "consumerKey", "consumerSecret");
        WooCommerce wooCommerce = new WooCommerceAPI(config, ApiVersionType.V3);

        // Prepare object for request
        Map<String, Object> productInfo = new HashMap<>();
        productInfo.put("name", "Premium Quality");
        productInfo.put("type", "simple");
        productInfo.put("regular_price", "21.99");
        productInfo.put("description", "Pellentesque habitant morbi tristique senectus et netus");

        // Make request and retrieve result
        Map product = wooCommerce.create(EndpointBaseType.PRODUCTS.getValue(), productInfo);

        System.out.println(product.get("id"));

        // Get all with request parameters
        Map<String, String> params = new HashMap<>();
        params.put("per_page","100");
        params.put("offset","0");
        List products = wooCommerce.getAll(EndpointBaseType.PRODUCTS.getValue(), params);

        System.out.println(products.size());
    }
```
