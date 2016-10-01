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
     * @param endpointBase API endpoint base @see EndpointBaseType
     * @param object       Map with entity properties and values
     * @return Map with created entity
     */
    Map create(String endpointBase, Map<String, Object> object);

    /**
     * Retrieves on WooCommerce entity
     *
     * @param endpointBase API endpoint base @see EndpointBaseType
     * @param id           id of WooCommerce entity
     * @return Retrieved WooCommerce entity
     */
    Map get(String endpointBase, int id);

    /**
     * Retrieves all WooCommerce entities
     *
     * @param endpointBase API endpoint base @see EndpointBaseType
     * @return List of retrieved entities
     */
    List getAll(String endpointBase);

    /**
     * Updates WooCommerce entity
     *
     * @param endpointBase API endpoint base @see EndpointBaseType
     * @param id           id of the entity to update
     * @param object       Map with updated properties
     * @return updated WooCommerce entity
     */
    Map update(String endpointBase, int id, Map<String, Object> object);

    /**
     * Deletes WooCommerce entity
     *
     * @param endpointBase API endpoint base @see EndpointBaseType
     * @param id           id of the entity to update
     * @return deleted WooCommerce entity
     */
    Map delete(String endpointBase, int id);

}
