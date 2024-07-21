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

        Router router = new Router();
        router.setId(1);
        router.setName("Router1");
        router.setLocationId(1);
        router.setRouterLinks(Arrays.asList(2, 3));

        String json = objectMapper.writeValueAsString(router);
        Router res = objectMapper.readValue(json, Router.class);

        assertEquals(router.getId(), res.getId());
        assertEquals(router.getName(), res.getName());
        assertEquals(router.getLocationId(), res.getLocationId());
        assertEquals(router.getRouterLinks(), res.getRouterLinks());
    }
}
