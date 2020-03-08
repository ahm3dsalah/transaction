package com.test.api.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import spark.Request;

public class RequestMapperUtil<T> {

    public T mapRequest(Request request, Class<T> requestClass) throws JsonProcessingException {
       return new ObjectMapper().readValue(request.body(), requestClass);
    }
}
