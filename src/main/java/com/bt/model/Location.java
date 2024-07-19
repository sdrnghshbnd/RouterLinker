package com.bt.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Represents a postal location.
 * This class has information about a location, including its unique identifier,
 * postal code, and name.
 */
@Data
public class Location {
    /**
     * The unique identifier for the location.
     */
    private int id;

    /**
     * The postal code of the location, mapped from the JSON property {@code postcode}.
     */
    @JsonProperty("postcode")
    private String postCode;

    /**
     * The name of the location.
     */
    private String name;
}
