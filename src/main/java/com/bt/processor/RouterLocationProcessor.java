package com.bt.processor;

import com.bt.util.ApiClient;
import com.bt.util.ConfigLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;

public class RouterLocationProcessor {
    private static final Logger logger = LoggerFactory.getLogger(RouterLocationProcessor.class);
    private final ApiClient apiClient;
    private final String apiUrl;

    // Constructor with ApiClient for easier testing
    public RouterLocationProcessor(ApiClient apiClient) {
        this.apiClient = apiClient;
        this.apiUrl = ConfigLoader.getProperty("API_URL");
        logger.debug("Initialized RouterLocationProcessor with API URL: {}", apiUrl);
    }

    // Default constructor for normal operation
    public RouterLocationProcessor() {
        this(new ApiClient());
    }

    public void process() throws Exception {
        logger.info("Starting data processing");
        try {
            String jsonResponse = apiClient.fetchJsonFromApi(apiUrl);
            Set<String> connections = new DataProcessor().processData(jsonResponse);

            System.out.println("\n---------- Connections ----------\n");
            connections.forEach(System.out::println);
            System.out.println("\n--------------------------------- \n");

            logger.info("Processed {} connections", connections.size());
        } catch (Exception e) {
            logger.error("Error during data processing", e);
            throw e; // Re-throw to be caught in Main
        }
        logger.info("Data processing completed");
    }
}
