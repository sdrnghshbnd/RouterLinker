package com.bt.model;

import lombok.Data;
import java.util.List;

/**
 * Represents the response received from the API, containing lists of routers and locations.
 * It includes the list of routers and the list of locations provided by the API.
 */
@Data
public class ApiResponse {
    /**
     * A list of {@link Router} objects representing the routers included in the API response.
     */
    private List<Router> routers;

    /**
     * A list of {@link Location} objects representing the locations included in the API response.
     */
    private List<Location> locations;
}
