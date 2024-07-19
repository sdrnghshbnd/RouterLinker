package com.bt.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.ConnectException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ApiClient {
    private static final Logger logger = LoggerFactory.getLogger(ApiClient.class);
    private final HttpClient httpClient;

    public ApiClient(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    public ApiClient() {
        this(HttpClient.newHttpClient());
    }

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
