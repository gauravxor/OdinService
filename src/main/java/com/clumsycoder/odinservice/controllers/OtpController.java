package com.clumsycoder.odinservice.controllers;

import com.clumsycoder.controlshift.commons.enums.OtpPurpose;
import com.clumsycoder.controlshift.commons.response.ApiResponse;
import com.clumsycoder.controlshift.commons.response.ApiResult;
import com.clumsycoder.odinservice.dto.request.GenerateOtpRequest;
import com.clumsycoder.odinservice.dto.request.ValidateOtpRequest;
import com.clumsycoder.odinservice.exception.verification.OtpException;
import com.clumsycoder.odinservice.services.OtpService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth")
@AllArgsConstructor
public class OtpController {

    private final OtpService otpService;

    @PostMapping("/verify-email")
    public ResponseEntity<ApiResult> verifyEmail(@Valid @RequestBody ValidateOtpRequest request) {
        otpService.validateEmailVerificationOtp(request);
        return new ResponseEntity<>(
                new ApiResult()
                        .statusCode(HttpStatus.OK.value())
                        .message("Email verified successfully"),
                HttpStatus.OK
        );
    }

    @PostMapping("/send-otp")
    public ResponseEntity<ApiResponse> resendOtp(@Valid @RequestBody GenerateOtpRequest request) {
        OtpPurpose otpType = request.getOtpType();

        switch (otpType) {
            case OtpPurpose.EMAIL_VERIFICATION -> otpService.sendEmailVerificationOtp("test@test.com", "123123");
            default -> throw new OtpException("Invalid OTP type received " + otpType);
        }

        return new ResponseEntity<>(
                new ApiResult()
                        .statusCode(HttpStatus.OK.value())
                        .message("OTP sent successfully"),
                HttpStatus.OK
        );
    }
}