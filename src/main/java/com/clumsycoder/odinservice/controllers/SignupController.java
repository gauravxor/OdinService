package com.clumsycoder.odinservice.controllers;

import com.clumsycoder.controlshift.commons.response.ApiResult;
import com.clumsycoder.odinservice.dto.Player;
import com.clumsycoder.odinservice.dto.request.SignupRequest;
import com.clumsycoder.odinservice.services.SignupService;
import com.clumsycoder.controlshift.commons.response.ApiResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/auth/signup")
@AllArgsConstructor
public class SignupController {
    private final SignupService signupService;

    @PostMapping("")
    public ResponseEntity<ApiResponse> signup(@Valid @RequestBody SignupRequest request) {
        Player newPlayer = signupService.signUp(request);
        return new ResponseEntity<>(
                new ApiResult().message("User created").data(Map.of("player", newPlayer)),
                HttpStatus.CREATED
        );
    }
}