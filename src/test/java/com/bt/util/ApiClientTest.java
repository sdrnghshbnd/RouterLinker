package com.bt.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ApiClientTest {

    @Mock
    private HttpClient mockHttpClient;

    @Mock
    private HttpResponse<String> mockHttpResponse;

    @InjectMocks
    private ApiClient apiClient;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFetchJsonFromApi_Success() throws Exception {
        String apiUrl = "https://api.example.com";
        String expectedResponse = "{\"key\":\"value\"}";

        when(mockHttpClient.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class)))
                .thenReturn(mockHttpResponse);
        when(mockHttpResponse.statusCode()).thenReturn(200);
        when(mockHttpResponse.body()).thenReturn(expectedResponse);

        String result = apiClient.fetchJsonFromApi(apiUrl);

        // Assert
        assertEquals(expectedResponse, result);
        verify(mockHttpClient).send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class));
    }

    @Test
    void testFetchJsonFromApi_Failure() {
        String invalidUrl = "http://invalid-url";
        ApiClient apiClient = new ApiClient(HttpClient.newHttpClient());

        // Assert
        Exception exception = assertThrows(Exception.class, () -> {
            apiClient.fetchJsonFromApi(invalidUrl);
        });

        // Check the exception message
        assertTrue(exception.getMessage().contains("Connection error fetching data from API"));
    }
}