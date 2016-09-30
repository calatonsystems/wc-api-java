package com.icoderman.woocommerce;

public enum WooCommerceEntity {

    COUPONS("coupons"),
    CUSTOMERS("customers"),
    ORDERS("orders"),
    PRODUCTS("products"),
    PRODUCTS_ATTRIBUTES("products/attributes"),
    PRODUCTS_CATEGORIES("products/categories"),
    PRODUCTS_SHIPPING_CLASSES("products/shipping_classes"),
    PRODUCTS_TAGS("products/tags"),
    REPORTS("reports"),
    REPORTS_SALES("reports/sales"),
    REPORTS_TOP_SELLERS("reports/top_sellers"),
    TAXES("taxes"),
    TAXES_CLASSES("taxes/classes"),
    WEBHOOKS("webhooks");

    private String path;

    WooCommerceEntity(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
