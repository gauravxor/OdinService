package com.clumsycoder.odinservice.controllers;

import com.clumsycoder.odinservice.dto.common.Player;
import com.clumsycoder.odinservice.dto.request.LoginRequest;
import com.clumsycoder.odinservice.services.JwtService;
import com.clumsycoder.odinservice.services.LoginService;
import com.clumsycoder.controlshift.commons.response.ApiResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class LoginController {
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
    private final LoginService loginService;
    private final JwtService jwtService;

    @PostMapping("/login")
    public ResponseEntity<ApiResponse> login(@Valid @RequestBody LoginRequest request) {

        Player player = loginService.login(request);

        String accessToken = jwtService.createAccessToken(player);

        ApiResponse response = new ApiResponse()
                .message("Logged in successfully")
                .data(Map.of(
                        "player", player,
                        "accessToken", accessToken
                ));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}