package com.clumsycoder.odinservice.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ValidateOtpRequest {

    @NotBlank(message = "Player ID is required")
    @Size(max = 50, message = "Player ID must not exceed 50 characters")
    @JsonProperty("playerId")
    private String playerId;

    @NotBlank(message = "OTP code is required")
    @Size(min = 4, max = 8, message = "OTP code must be between 4 and 8 characters")
    @JsonProperty("otpCode")
    private String otpCode;

    @Override
    public String toString() {
        return "OtpValidationRequestDto{" +
                "playerId='" + playerId + '\'' +
                ", otpCode='[PROTECTED]'" +
                '}';
    }
}