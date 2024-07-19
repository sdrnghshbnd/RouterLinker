package com.bt.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RouterTest {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void testSerialization() throws IOException {
        // Arrange
        Router router = new Router();
        router.setId(1);
        router.setName("Router1");
        router.setLocationId(1);
        router.setRouterLinks(Arrays.asList(2, 3));

        // Act
        String json = objectMapper.writeValueAsString(router);
        Router deserializedRouter = objectMapper.readValue(json, Router.class);

        // Assert
        assertEquals(router.getId(), deserializedRouter.getId());
        assertEquals(router.getName(), deserializedRouter.getName());
        assertEquals(router.getLocationId(), deserializedRouter.getLocationId());
        assertEquals(router.getRouterLinks(), deserializedRouter.getRouterLinks());
    }
}
