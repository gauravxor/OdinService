package com.clumsycoder.odinservice.dto.internal;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlayerAuthResponse {

    @JsonProperty("id")
    private String id;

    @JsonProperty("email")
    private String email;

    @JsonProperty("password")
    private String password;

    @JsonProperty("isEmailVerified")
    private Boolean isEmailVerified;

    @Override
    public String toString() {
        return "PlayerAuthResponseDto{" +
                "id='" + id + '\'' +
                ", email='" + email + '\'' +
                ", password='[PROTECTED]'" +
                ", isEmailVerified=" + isEmailVerified +
                '}';
    }
}