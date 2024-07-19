package com.bt.model;

import lombok.Data;

import java.util.List;

@Data
public class ApiResponse {
    private List<Router> routers;
    private List<Location> locations;
}
