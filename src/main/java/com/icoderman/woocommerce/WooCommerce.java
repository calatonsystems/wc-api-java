package com.icoderman.woocommerce;

import java.util.Collections;
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
     * Retrieves all WooCommerce entities with request parameters
     *
     * @param endpointBase API endpoint base @see EndpointBaseType
     * @param params additional request params
     * @return List of retrieved entities
     */
    Page getAll(String endpointBase, Map<String, String> params);

    /**
     * Retrieves all WooCommerce entities
     *
     * @param endpointBase API endpoint base @see EndpointBaseType
     * @return List of retrieved entities
     */
    default Page getAll(String endpointBase) {
        return getAll(endpointBase, Collections.emptyMap());
    }

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

    /**
     * Makes batch operations on WooCommerce entities
     *
     * @param endpointBase API endpoint base @see EndpointBaseType
     * @param object       Map with lists of entities
     * @return response Map with WooCommerce entities implicated
     */
    Map batch(String endpointBase, Map<String, Object> object);

}
