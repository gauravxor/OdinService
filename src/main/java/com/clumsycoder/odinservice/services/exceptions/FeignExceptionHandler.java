package com.clumsycoder.odinservice.services.exceptions;

import com.clumsycoder.controlshift.commons.exceptions.DuplicateResourceException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.FeignException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class FeignExceptionHandler {
    public RuntimeException handle(FeignException e) {
        int status = e.status();
        try {
            String content = e.contentUTF8();
            ObjectMapper mapper = new ObjectMapper();
            JsonNode json = mapper.readTree(content);
            String message = json.has("message") ? json.get("message").asText() : "Unknown error";

            if (status == HttpStatus.CONFLICT.value())
                return new DuplicateResourceException(message);

            return new RuntimeException(message);

        } catch (Exception exc) {
            return new RuntimeException("Failed to parse error from downstream service");
        }
    }
}