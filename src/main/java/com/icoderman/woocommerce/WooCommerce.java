package com.icoderman.woocommerce;

public interface WooCommerce {

    Object create(String entity, Object object);
    Object get(String entity, int id);
    Object getAll(String entity);
    Object update(String entity, int id, Object object);
    Object delete(String entity, int id, boolean force);

}
