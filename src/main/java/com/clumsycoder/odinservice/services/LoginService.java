package com.clumsycoder.odinservice.services;

import com.clumsycoder.controlshift.commons.exceptions.ResourceNotFoundException;
import com.clumsycoder.odinservice.clients.NucleusServiceClient;
import com.clumsycoder.odinservice.dto.Player;
import com.clumsycoder.odinservice.dto.request.LoginRequest;
import com.clumsycoder.odinservice.models.PlayerAuth;
import com.clumsycoder.odinservice.repositories.PlayerAuthRepository;
import com.clumsycoder.controlshift.commons.exceptions.UnauthorizedException;
import com.clumsycoder.odinservice.services.exceptions.FeignExceptionHandler;
import feign.FeignException;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LoginService {
    private final NucleusServiceClient nucleusServiceClient;
    private final PasswordEncoder passwordEncoder;
    private final PlayerAuthRepository playerAuthRepository;
    private final FeignExceptionHandler feignExceptionHandler;

    public Player login(LoginRequest request) {
        PlayerAuth playerAuth = playerAuthRepository
                .findByEmail((request.getEmail()))
                .orElseThrow(() -> new ResourceNotFoundException("Player does not exist."));

        String hashedPassword = playerAuth.getPasswordHash();
        String password = request.getPassword();
        if (!passwordEncoder.matches(password, hashedPassword)) {
            throw new UnauthorizedException("Invalid password provided.");
        }

        try {
            return nucleusServiceClient.getPlayerById(playerAuth.getPlayerId());
        } catch (FeignException e) {
            throw feignExceptionHandler.handle(e);
        }
    }
}