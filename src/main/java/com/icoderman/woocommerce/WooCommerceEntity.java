package com.icoderman.woocommerce;

public enum WooCommerceEntity {
    PRODUCTS("/products"),
    PRODUCTS_CATEGORIES("/products/categories");

    private String slug;

    WooCommerceEntity(String slug) {
        this.slug = slug;
    }

    public String getSlug() {
        return slug;
    }
}
