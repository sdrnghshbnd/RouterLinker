package com.bt.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.ConnectException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * A client for making HTTP requests to fetch data from an API.
 * This class handles HTTP communication with a given API URL and retrieves data
 * in the form of a JSON string. It uses {@link HttpClient} for sending requests
 * and handles possible errors during the request process.
 */
public class ApiClient {
    private static final Logger logger = LoggerFactory.getLogger(ApiClient.class);
    private final HttpClient httpClient;

    /**
     * Creates an instance of {@code ApiClient} with a custom {@link HttpClient}.
     *
     * @param httpClient the {@code HttpClient} to use for making requests.
     */
    ApiClient(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    /**
     * Creates an instance of {@code ApiClient} with a default {@link HttpClient}.
     */
    public ApiClient() {
        this(HttpClient.newHttpClient());
    }

    /**
     * Fetches JSON data from the specified API URL.
     * <p>
     * Makes an HTTP GET request to the provided URL and returns the response body
     * as a string. Throws an exception if the request fails or if the status code
     * is not 200 OK.
     * </p>
     *
     * @param apiUrl the URL of the API endpoint to fetch data from.
     * @return the response body as a string.
     * @throws Exception if an error occurs during the request or if the status code
     *         is not 200.
     */
    public String fetchJsonFromApi(String apiUrl) throws Exception {
        logger.debug("Fetching data from API: {}", apiUrl);
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(apiUrl))
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() != 200) {
                logger.error("API request failed with status code: {}", response.statusCode());
                throw new Exception("API request failed with status code: " + response.statusCode());
            }
            logger.debug("Successfully fetched data from API");
            return response.body();
        } catch (ConnectException e) {
            logger.error("Connection error while fetching data from API", e);
            throw new Exception("Connection error fetching data from API", e);
        } catch (Exception e) {
            logger.error("Error fetching data from API", e);
            throw new Exception("Error fetching data from API", e);
        }
    }
}
