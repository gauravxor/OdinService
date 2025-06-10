package com.clumsycoder.odinservice.controllers;

import com.clumsycoder.controlshift.commons.response.ApiResult;
import com.clumsycoder.odinservice.dto.Player;
import com.clumsycoder.odinservice.dto.request.LoginRequest;
import com.clumsycoder.odinservice.services.JwtService;
import com.clumsycoder.odinservice.services.LoginService;
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
    public ResponseEntity<ApiResult> login(@Valid @RequestBody LoginRequest request) {
        String email = request.getEmail();
        logger.info("Login attempt received for email: {}", email);

        Player player = loginService.login(request);
        String accessToken = jwtService.createAccessToken(player);

        logger.info("Login successful for userId: {}", player.getId());

        ApiResult result = new ApiResult()
                .message("Logged in successfully")
                .statusCode(HttpStatus.OK.value())
                .data(Map.of(
                        "player", player,
                        "accessToken", accessToken
                ));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}