package org.study.common.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.study.common.ui.Response;

public class ResponseObjectMapper {

    private ResponseObjectMapper() {}

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static Response toResponseObject(String response) {
        try {
            return objectMapper.readValue(response, Response.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static String toStringResponse(Response<?> response) {
        try {
            return objectMapper.writeValueAsString(response);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
