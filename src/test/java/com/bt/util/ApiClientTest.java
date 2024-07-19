package com.bt.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpHeaders;
import java.net.URI;
import java.util.Map;
import java.util.Optional;
import javax.net.ssl.SSLSession;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class ApiClientTest {

    @Mock
    private HttpClient mockHttpClient;

    private ApiClient apiClient;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        apiClient = new ApiClient(mockHttpClient);
    }

    @Test
    void testFetchJsonFromApi_Success() throws Exception {
        // Arrange
        String expectedJson = "{\"key\":\"value\"}";
        HttpResponse<String> mockResponse = new MockHttpResponse<>(200, expectedJson);
        when(mockHttpClient.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class)))
                .thenReturn(mockResponse);

        // Act
        String result = apiClient.fetchJsonFromApi("http://test.com");

        // Assert
        assertEquals(expectedJson, result);
    }

    @Test
    void testFetchJsonFromApi_Failure() throws Exception {
        // Arrange
        HttpResponse<String> mockResponse = new MockHttpResponse<>(404, "Not Found");
        when(mockHttpClient.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class)))
                .thenReturn(mockResponse);

        // Act & Assert
        assertThrows(Exception.class, () -> apiClient.fetchJsonFromApi("http://test.com"));
    }

    @Test
    void testFetchJsonFromApi_IOExceptionHandling() throws Exception {
        // Arrange
        when(mockHttpClient.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class)))
                .thenThrow(new IOException("Network error"));

        // Act & Assert
        assertThrows(Exception.class, () -> apiClient.fetchJsonFromApi("http://test.com"));
    }

    private static class MockHttpResponse<T> implements HttpResponse<T> {
        private final int statusCode;
        private final T body;

        MockHttpResponse(int statusCode, T body) {
            this.statusCode = statusCode;
            this.body = body;
        }

        @Override
        public int statusCode() {
            return statusCode;
        }

        @Override
        public T body() {
            return body;
        }

        // Implement other methods of HttpResponse interface with default implementations
        @Override
        public HttpRequest request() {
            return null;
        }

        @Override
        public Optional<HttpResponse<T>> previousResponse() {
            return Optional.empty();
        }

        @Override
        public HttpHeaders headers() {
            return HttpHeaders.of(Map.of(), (s1, s2) -> true);
        }

        @Override
        public Optional<SSLSession> sslSession() {
            return Optional.empty();
        }

        @Override
        public URI uri() {
            return null;
        }

        @Override
        public HttpClient.Version version() {
            return null;
        }
    }
}