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

    /**
     * Constructor that accepts an {@link ApiClient} instance for easier testing.
     *
     * @param apiClient the ApiClient instance to be used for fetching data.
     */
    public RouterLocationProcessor(ApiClient apiClient) {
        this.apiClient = apiClient;
        this.apiUrl = ConfigLoader.getProperty("API_URL");
        logger.info("Initialized RouterLocationProcessor with API URL: {}", apiUrl);
    }

    /**
     * Default constructor for normal operation.
     * It initializes the {@link ApiClient} with a default configuration.
     */
    public RouterLocationProcessor() {
        this(new ApiClient());
    }

    /**
     * Fetches data from the API, processes it to find connections between routers, and prints the connections.
     *
     * @throws Exception if an error occurs during data fetching or processing.
     */
    public void process() throws Exception {
        logger.info("Starting data processing");
        try {
            String jsonResponse = apiClient.fetchJsonFromApi(apiUrl);
            Set<String> connections = new DataProcessor().processData(jsonResponse);

            // Print connections to the console
            System.out.println("\n---------- Connections ----------\n");
            connections.forEach(System.out::println);
            System.out.println("\n--------------------------------- \n");

            logger.info("Processed {} connections", connections.size());
        } catch (Exception e) {
            logger.error("Error during data processing", e);
            throw e; // to caught it in Main
        }
        logger.info("Data processing completed");
    }
}
