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
    Map create(String entityPath, Map<String, Object> object);

    /**
     * Retrieves on WooCommerce entity
     *
     * @param entityPath WooCommerce entity @see WooCommerceEntity
     * @param id         id of WooCommerce entity
     * @return Retrieved WooCommerce entity
     */
    Map get(String entityPath, int id);

    /**
     * Retrieves all WooCommerce entities
     *
     * @param entityPath entity path
     * @return List of retrieved entities
     */
    List getAll(String entityPath);

    /**
     * Updates WooCommerce entity
     *
     * @param entityPath entity path
     * @param id         id of the entity to update
     * @param object     Map with updated properties
     * @return updated WooCommerce entity
     */
    Map update(String entityPath, int id, Map<String, Object> object);

    /**
     * Deletes WooCommerce entity
     *
     * @param entityPath entity path
     * @param id         id of the entity to update
     * @return deleted WooCommerce entity
     */
    Map delete(String entityPath, int id);

}
