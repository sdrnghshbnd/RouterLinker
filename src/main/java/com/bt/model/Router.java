package com.bt.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class Router {
    private int id;
    private String name;

    @JsonProperty("location_id")
    private int locationId;

    @JsonProperty("router_links")
    private List<Integer> routerLinks;
}