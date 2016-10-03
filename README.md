# WooCommerce API Java Wrapper
Java wrapper for WooCommerce REST API.

## Setup
wc-api-java is available on maven central:
```xml
<dependency>
    <groupId>com.icoderman</groupId>
    <artifactId>wc-api-java</artifactId>
    <version>1.0</version>
</dependency>
```

## Usage

```java
    public static void main(String[] args) {
        // Setup client
        WooCommerceConfig config = new WooCommerceConfig("http://woocommerce.com", "consumerKey", "consumerSecret");
        WooCommerce wooCommerce = new WooCommerceAPI(config);

        // Prepare object for request
        Map<String, Object> productInfo = new HashMap<>();
        productInfo.put("name", "Premium Quality");
        productInfo.put("type", "simple");
        productInfo.put("regular_price", "21.99");
        productInfo.put("description", "Pellentesque habitant morbi tristique senectus et netus");

        // Make request and retrieve result
        Map product = wooCommerce.create(EndpointBaseType.PRODUCTS.getValue(), productInfo);

        System.out.println(product.get("id"));
    }
```
