package com.bt.processor;

import com.bt.util.ApiClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RouterLocationProcessorTest {

    @Mock
    private ApiClient apiClient;

    @InjectMocks
    private RouterLocationProcessor processor;

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outContent));
    }

    @Test
    void process_ValidData_PrintsCorrectConnections() throws Exception {

        String jsonResponse = "{\"routers\":[{\"id\":1,\"name\":\"Router1\",\"location_id\":1,\"router_links\":[2]}," +
                "{\"id\":2,\"name\":\"Router2\",\"location_id\":2,\"router_links\":[1]}]," +
                "\"locations\":[{\"id\":1,\"name\":\"Location1\"},{\"id\":2,\"name\":\"Location2\"}]}";
        when(apiClient.fetchJsonFromApi(anyString())).thenReturn(jsonResponse);

        processor.process();

        String output = outContent.toString();
        assertTrue(output.contains("---------- Connections ----------"));
        assertTrue(output.contains("Location1 <-> Location2"));
    }

    @Test
    void process_ApiError_ThrowsException() throws Exception {

        when(apiClient.fetchJsonFromApi(anyString())).thenThrow(new Exception("API Error"));

        Exception exception = assertThrows(Exception.class, () -> processor.process());
        assertEquals("API Error", exception.getMessage());
    }
}
