package com.bt.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LocationTest {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void testSerialization() throws IOException {

        Location location = new Location();
        location.setId(1);
        location.setPostCode("AB1 2CD");
        location.setName("Location1");

        String json = objectMapper.writeValueAsString(location);
        Location deserializedLocation = objectMapper.readValue(json, Location.class);

        assertEquals(location.getId(), deserializedLocation.getId());
        assertEquals(location.getPostCode(), deserializedLocation.getPostCode());
        assertEquals(location.getName(), deserializedLocation.getName());
    }
}
