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

    public Set<String> processData(String jsonResponse) throws Exception {
        logger.info("Starting data processing");
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            ApiResponse apiResponse = objectMapper.readValue(jsonResponse, ApiResponse.class);
            Map<Integer, String> locationMap = createLocationMap(apiResponse.getLocations());
            Set<String> connections = findConnections(apiResponse.getRouters(), locationMap);
            logger.info("Data processing completed. Found {} connections", connections.size());
            return connections;
        } catch (Exception e) {
            logger.error("Error processing data", e);
            throw new Exception("Error processing data", e);
        }
    }

    private Map<Integer, String> createLocationMap(List<Location> locations) {
        Map<Integer, String> locationMap = new HashMap<>();
        for (Location location : locations) {
            locationMap.put(location.getId(), location.getName());
        }
        logger.debug("Created location map with {} entries", locationMap.size());
        return locationMap;
    }

    private Set<String> findConnections(List<Router> routers, Map<Integer, String> locationMap) {
        Set<String> connections = new HashSet<>();
        Map<Integer, Router> routerMap = new HashMap<>();

        // Populate the routerMap for quick lookup
        for (Router router : routers) {
            routerMap.put(router.getId(), router);
        }

        for (Router router : routers) {
            String location1 = locationMap.get(router.getLocationId());
            for (int linkedRouterId : router.getRouterLinks()) {
                Router linkedRouter = routerMap.get(linkedRouterId);
                if (linkedRouter != null) {
                    String location2 = locationMap.get(linkedRouter.getLocationId());
                    if (!location1.equals(location2)) {
                        connections.add(ConnectionFormatter.formatConnection(location1, location2));
                    }
                }
            }
        }

        logger.debug("Found {} unique connections", connections.size());
        return connections;
    }
}