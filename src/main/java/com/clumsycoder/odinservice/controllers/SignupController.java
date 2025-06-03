package com.clumsycoder.odinservice.controllers;

import com.clumsycoder.odinservice.dto.common.Player;
import com.clumsycoder.odinservice.dto.request.PlayerSignupRequest;
import com.clumsycoder.odinservice.services.SignupService;
import com.clumsycoder.odinservice.services.exceptions.FeignExceptionHandler;
import com.clumsycoder.controlshift.commons.response.ApiResponse;
import feign.FeignException;
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
    private final FeignExceptionHandler feignExceptionHandler;


    @PostMapping("")
    public ResponseEntity<ApiResponse> signup(@Valid @RequestBody PlayerSignupRequest request) {
        try {
            Player newPlayer = signupService.createPlayer(request);
            ApiResponse response = new ApiResponse()
                    .message("Player created")
                    .data(Map.of("player", newPlayer));
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (FeignException e) {
            throw feignExceptionHandler.handle(e);
        }
    }
}