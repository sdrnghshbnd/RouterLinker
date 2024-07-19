package com.bt.processor;

import com.bt.util.ApiClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RouterLocationProcessorTest {

    @Mock
    private ApiClient apiClient;  // Mock ApiClient

    @InjectMocks
    private RouterLocationProcessor processor;  // Inject mock ApiClient

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        System.setOut(new PrintStream(outContent));  // Capture standard output
    }

    @Test
    void process_ValidData_PrintsCorrectConnections() throws Exception {
        // Arrange
        String jsonResponse = "{\"routers\":[{\"id\":1,\"name\":\"Router1\",\"location_id\":1,\"router_links\":[2]}," +
                "{\"id\":2,\"name\":\"Router2\",\"location_id\":2,\"router_links\":[1]}]," +
                "\"locations\":[{\"id\":1,\"name\":\"Location1\"},{\"id\":2,\"name\":\"Location2\"}]}";
        when(apiClient.fetchJsonFromApi(anyString())).thenReturn(jsonResponse);

        // Act
        processor.process();

        // Assert
        String output = outContent.toString();
        assertTrue(output.contains("---------- Connections ----------"));
        assertTrue(output.contains("Location1 <-> Location2"));
    }

    @Test
    void process_ApiError_ThrowsException() throws Exception {
        // Arrange
        when(apiClient.fetchJsonFromApi(anyString())).thenThrow(new Exception("API Error"));

        // Act & Assert
        Exception exception = assertThrows(Exception.class, () -> processor.process());
        assertEquals("API Error", exception.getMessage());
    }
}
