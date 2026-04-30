package com.forum.api.infra.adapter.out.api;

import lombok.Data;
import lombok.NoArgsConstructor;
import tools.jackson.databind.JsonNode;

import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
public class ApiResponse<T> {
    private String get;
    private Map<String, Object> parameters;
    private JsonNode errors;
    private int results;
    private List<T> response;


}
