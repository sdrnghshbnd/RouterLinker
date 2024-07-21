package com.bt.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ApiResponseTest {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void testSerialization() throws IOException {
        Location location1 = new Location();
        location1.setId(1);
        location1.setPostCode("AB1 2CD");
        location1.setName("Location1");

        Router router1 = new Router();
        router1.setId(1);
        router1.setName("Router1");
        router1.setLocationId(1);
        router1.setRouterLinks(List.of(2));

        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setLocations(List.of(location1));
        apiResponse.setRouters(List.of(router1));

        String json = objectMapper.writeValueAsString(apiResponse);
        ApiResponse res = objectMapper.readValue(json, ApiResponse.class);

        assertEquals(apiResponse.getLocations(), res.getLocations());
        assertEquals(apiResponse.getRouters(), res.getRouters());
    }
}