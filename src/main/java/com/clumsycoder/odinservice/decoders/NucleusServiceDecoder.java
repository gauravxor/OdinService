package com.clumsycoder.odinservice.decoders;

import com.clumsycoder.controlshift.commons.nucleusservice.ErrorCode;
import com.clumsycoder.odinservice.exception.nucleusservice.EmailAlreadyUsedException;
import com.clumsycoder.odinservice.exception.nucleusservice.NucleusServiceException;
import com.clumsycoder.odinservice.exception.nucleusservice.NucleusValidationException;
import com.clumsycoder.odinservice.exception.nucleusservice.UserNotFoundException;
import com.clumsycoder.odinservice.exception.nucleusservice.UsernameAlreadyUsedException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;

@Component
@AllArgsConstructor
public class NucleusServiceDecoder implements ErrorDecoder {
    private final ObjectMapper objectMapper;
    private static final String FALLBACK_ERROR_CODE = ErrorCode.INTERNAL_ERROR.name();
    private static final Logger logger = LoggerFactory.getLogger(NucleusServiceDecoder.class);

    public static ErrorCode getNucleusErrorCode(String errorCode) {
        try {
            return ErrorCode.valueOf(errorCode);
        } catch (IllegalArgumentException e) {
            logger.warn("Nucleus service returned an error code {} that was not identified", errorCode);
            return ErrorCode.INTERNAL_ERROR;
        }
    }

    public String getReceivedErrorCode(Response response) {
        try {
            InputStream inputStream = response.body().asInputStream();
            byte[] boldBytes = inputStream.readAllBytes();
            String responseBody = new String(boldBytes);

            JsonNode jsonNode = objectMapper.readTree(responseBody);
            JsonNode errorCodeNode = jsonNode.get("errorCode");

            if (errorCodeNode != null && !errorCodeNode.isNull()) {
                return errorCodeNode.asText();
            }
            logger.warn("errorCode field not found in response from NucleusService");
        } catch (IOException e) {
            logger.error("Failed to parse the response body", e);
        } catch (Exception e) {
            logger.error("Unexpected error while extracting errorCode", e);
        }
        return FALLBACK_ERROR_CODE;
    }

    @Override
    public Exception decode(String methodKey, Response response) {
        try {
            if (response.body() != null) {
                String errorCode = getReceivedErrorCode(response);
                return switch (getNucleusErrorCode(errorCode)) {
                    case EMAIL_CONFLICT -> new EmailAlreadyUsedException();
                    case USERNAME_CONFLICT -> new UsernameAlreadyUsedException();
                    case USER_NOT_FOUND -> new UserNotFoundException();
                    case VALIDATION_ERROR -> new NucleusValidationException();
                    case INTERNAL_ERROR -> new NucleusServiceException();
                };
            }
            logger.warn("Nucleus service response is Null");
        } catch (Exception e) {
            logger.error("Error decoding response from Nucleus Service.", e);
        }
        throw new NucleusServiceException();
    }
}