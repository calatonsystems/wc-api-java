package com.icoderman.woocommerce;

import java.util.List;

public interface WooCommerce {

    Object create(WooCommerceEntity entity, Object object);
    Object get(WooCommerceEntity entity, int id);
    Object getAll(WooCommerceEntity entity);
    Object update(WooCommerceEntity entity, int id, Object object);
    Object delete(WooCommerceEntity entity, int id);

}
