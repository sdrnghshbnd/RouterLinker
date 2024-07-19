package com.bt.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * Represents a network router with its details.
 * This class holds the data for a router including its unique identifier, name,
 * associated location, and the list of IDs for other routers it is linked to.
 */
@Data
public class Router {
    /**
     * The unique identifier of the router.
     */
    private int id;

    /**
     * The name of the router.
     */
    private String name;

    /**
     * The ID of the location where the router is situated.
     */
    @JsonProperty("location_id")
    private int locationId;

    /**
     * A list of IDs representing the routers to which this router is linked.
     */
    @JsonProperty("router_links")
    private List<Integer> routerLinks;
}
