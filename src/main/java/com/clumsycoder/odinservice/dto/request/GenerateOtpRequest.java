package com.clumsycoder.odinservice.dto.request;

import com.clumsycoder.controlshift.commons.enums.OtpPurpose;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GenerateOtpRequest {
    @NotNull
    private OtpPurpose otpType;  /* Use custom enum error handler */
}