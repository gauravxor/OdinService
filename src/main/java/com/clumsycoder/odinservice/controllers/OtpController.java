package com.clumsycoder.odinservice.controllers;

import com.clumsycoder.odinservice.dto.request.GenerateOtpRequest;
import com.clumsycoder.odinservice.dto.request.ValidateOtpRequest;
import com.clumsycoder.odinservice.services.OtpService;
import com.clumsycoder.controlshift.commons.enums.OtpPurpose;
import com.clumsycoder.controlshift.commons.exceptions.OtpException;
import com.clumsycoder.controlshift.commons.response.ApiResponse;
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
    public ResponseEntity<ApiResponse> verifyEmail(@Valid @RequestBody ValidateOtpRequest request) {

        boolean isVerified = otpService.validateEmailVerificationOtp(request);

        ApiResponse apiResponse = new ApiResponse();

        if (isVerified) {
            apiResponse.message("Email verified successfully");
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        }
        apiResponse.message("OTP expired or invalid OTP");
        return new ResponseEntity<>(apiResponse, HttpStatus.UNAUTHORIZED);
    }

    @PostMapping("/send-otp")
    public ResponseEntity<ApiResponse> resendOtp(@Valid @RequestBody GenerateOtpRequest request) {
        OtpPurpose otpType = request.getOtpType();
        switch (otpType) {
            case OtpPurpose.EMAIL_VERIFICATION -> otpService.sendEmailVerificationOtp("test@test.com", "123123");
            default -> throw new OtpException("Invalid OTP type received " + otpType);
        }

        ApiResponse apiResponse = new ApiResponse();
        apiResponse.message("OTP sent successfully");

        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
}