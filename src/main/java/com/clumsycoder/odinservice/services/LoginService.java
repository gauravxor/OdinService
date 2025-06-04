package com.clumsycoder.odinservice.services;

import com.clumsycoder.controlshift.commons.exceptions.ResourceNotFoundException;
import com.clumsycoder.odinservice.clients.NucleusServiceClient;
import com.clumsycoder.odinservice.dto.common.Player;
import com.clumsycoder.odinservice.dto.internal.PlayerAuthResponse;
import com.clumsycoder.odinservice.dto.request.LoginRequest;
import com.clumsycoder.odinservice.models.PlayerAuth;
import com.clumsycoder.odinservice.repositories.PlayerAuthRepository;
import com.clumsycoder.odinservice.services.exceptions.FeignExceptionHandler;
import com.clumsycoder.controlshift.commons.exceptions.UnauthorizedException;
import feign.FeignException;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LoginService {
    private final NucleusServiceClient nucleusServiceClient;
    private final PasswordEncoder passwordEncoder;
    private final FeignExceptionHandler feignExceptionHandler;
    private final PlayerAuthRepository playerAuthRepository;

    public Player login(LoginRequest request) {
        try {
            PlayerAuth playerAuth = playerAuthRepository.getReferenceByEmail((request.getEmail()));

            if (!passwordEncoder.matches(request.getPassword(), playerAuth.getPasswordHash())) {
                throw new UnauthorizedException("Invalid password provided.");
            }

            return nucleusServiceClient.getPlayerById(playerAuth.getPlayerId());

        } catch (FeignException e) {
            throw feignExceptionHandler.handle(e);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Player does not exist.");
        }
    }
}