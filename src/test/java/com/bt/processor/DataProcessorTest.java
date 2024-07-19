package com.bt.processor;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class DataProcessorTest {

    private DataProcessor dataProcessor;

    @BeforeEach
    void setUp() {
        dataProcessor = new DataProcessor();
    }

    @Test
    void processData_ValidInput_ReturnsCorrectConnections() throws Exception {
        // Arrange
        String jsonResponse = "{\"routers\":[{\"id\":1,\"name\":\"Router1\",\"location_id\":1,\"router_links\":[2]}," +
                "{\"id\":2,\"name\":\"Router2\",\"location_id\":2,\"router_links\":[1]}]," +
                "\"locations\":[{\"id\":1,\"name\":\"Location1\"},{\"id\":2,\"name\":\"Location2\"}]}";

        // Act
        Set<String> result = dataProcessor.processData(jsonResponse);

        // Assert
        assertEquals(1, result.size());
        assertTrue(result.contains("Location1 <-> Location2"));
    }

    @Test
    void processData_InvalidJson_ThrowsException() {
        // Arrange
        String invalidJson = "{invalid json}";

        // Act & Assert
        assertThrows(Exception.class, () -> dataProcessor.processData(invalidJson));
    }
}
