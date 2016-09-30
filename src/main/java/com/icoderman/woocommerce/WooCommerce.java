package com.icoderman.woocommerce;

import java.util.List;
import java.util.Map;

/**
 * Main interface for WooCommerce REST API
 */
public interface WooCommerce {

    /**
     * Creates WooCommerce entity
     *
     * @param entityPath WooCommerce entity @see WooCommerceEntity
     * @param object     Map with entity properties and values
     * @return Map with created entity
     */
    Map<String, Object> create(String entityPath, Map<String, Object> object);

    /**
     * Retrieves on WooCommerce entity
     *
     * @param entity WooCommerce entity @see WooCommerceEntity
     * @param id     id of WooCommerce entity
     * @return Retrieved WooCommerce entity
     */
    Map<String, Object> get(String entity, int id);

    /**
     * Retrieves all WooCommerce entities
     *
     * @param entityPath entity path
     * @return List of retrieved entities
     */
    List<Map<String, Object>> getAll(String entityPath);

    /**
     * Updates WooCommerce entity
     *
     * @param entityPath entity path
     * @param id         id of the entity to update
     * @param object     Map with updated properties
     * @return updated WooCommerce entity
     */
    Map<String, Object> update(String entityPath, int id, Map<String, Object> object);

    /**
     * Deletes WooCommerce entity
     *
     * @param entityPath entity path
     * @param id         id of the entity to update
     * @return deleted WooCommerce entity
     */
    Map<String, Object> delete(String entityPath, int id);

}
