package com.bt.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Location {
    private int id;

    @JsonProperty("postcode")
    private String postCode;

    private String name;
}
