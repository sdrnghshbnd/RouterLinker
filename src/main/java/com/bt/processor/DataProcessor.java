package com.bt.processor;

import com.bt.model.ApiResponse;
import com.bt.model.Location;
import com.bt.model.Router;
import com.bt.util.ConnectionFormatter;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class DataProcessor {
    private static final Logger logger = LoggerFactory.getLogger(DataProcessor.class);

    /**
     * Processes the provided JSON response to find connections between routers.
     *
     * @param jsonResponse the JSON response containing routers and locations data.
     * @return a set of formatted strings representing connections between different locations.
     * @throws Exception if an error occurs during parsing or processing the data.
     */
    public Set<String> processData(String jsonResponse) throws Exception {
        logger.info("Starting data processing");
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            // Parse the JSON response to ApiResponse
            ApiResponse apiResponse = objectMapper.readValue(jsonResponse, ApiResponse.class);
            // Create a map of location IDs to location names
            Map<Integer, String> locationMap = createLocationMap(apiResponse.getLocations());
            // Find connections between routers
            Set<String> connections = findConnections(apiResponse.getRouters(), locationMap);
            logger.info("Data processing completed. Found {} connections", connections.size());
            return connections;
        } catch (Exception e) {
            logger.error("Error processing data", e);
            throw new Exception("Error processing data", e);
        }
    }

    /**
     * Creates a map from location IDs to location names.
     *
     * @param locations the list of locations to map.
     * @return a map where the key is the location ID and the value is the location name.
     */
    private Map<Integer, String> createLocationMap(List<Location> locations) {
        Map<Integer, String> locationMap = new HashMap<>();
        for (Location location : locations) {
            locationMap.put(location.getId(), location.getName());
        }
        logger.info("Location map is created with {} entries", locationMap.size());
        return locationMap;
    }

    /**
     * Finds and formats connections between routers based on their locations.
     *
     * @param routers     the list of routers to process.
     * @param locationMap a map of location IDs to location names.
     * @return a set of formatted strings representing connections between different locations.
     */
    private Set<String> findConnections(List<Router> routers, Map<Integer, String> locationMap) {
        Set<String> connections = new HashSet<>();
        Map<Integer, Integer> routerLocationMap = new HashMap<>();

        // Populate the routerLocationMap for quick lookup
        for (Router router : routers) {
            routerLocationMap.put(router.getId(), router.getLocationId());
        }

        for (Router router : routers) {
            String location1 = locationMap.get(router.getLocationId());
            for (int linkedRouterId : router.getRouterLinks()) {
                Integer linkedRouterLocationId = routerLocationMap.get(linkedRouterId);
                if (linkedRouterLocationId != null) {
                    String location2 = locationMap.get(linkedRouterLocationId);
                    if (!location1.equals(location2)) {
                        // If we find a connection like location1 <-> location2 and also location2 <-> location1,
                        // the only connection we see at the end is location1 <-> location2 because we formatted the connection
                        // to have the alphabetically smaller location first. Since we are using a set, we only keep location1 <-> location2

                        connections.add(ConnectionFormatter.formatConnection(location1, location2));
                    }
                }
            }
        }

        logger.info("{} unique connections found", connections.size());
        return connections;
    }
}