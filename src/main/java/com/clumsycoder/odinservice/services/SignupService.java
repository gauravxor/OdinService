package com.clumsycoder.odinservice.services;

import com.clumsycoder.controlshift.commons.exceptions.DatabaseException;
import com.clumsycoder.controlshift.commons.exceptions.DuplicateResourceException;
import com.clumsycoder.odinservice.clients.NucleusServiceClient;
import com.clumsycoder.odinservice.dto.Player;
import com.clumsycoder.odinservice.dto.internal.CreatePlayerRequest;
import com.clumsycoder.odinservice.dto.request.SignupRequest;
import com.clumsycoder.odinservice.models.PlayerAuth;
import com.clumsycoder.odinservice.repositories.PlayerAuthRepository;
import com.clumsycoder.odinservice.services.exceptions.FeignExceptionHandler;
import feign.FeignException;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SignupService {
    private final OtpService otpService;
    private final NucleusServiceClient nucleusServiceClient;
    private final PlayerAuthRepository playerAuthRepository;
    private final PasswordEncoder passwordEncoder;
    private final FeignExceptionHandler feignExceptionHandler;

    public Player signUp(SignupRequest request) {
        CreatePlayerRequest userServiceRequest = new CreatePlayerRequest();
        userServiceRequest.setEmail(request.getEmail());
        userServiceRequest.setFirstName(request.getFirstName());
        userServiceRequest.setLastName(request.getLastName());
        userServiceRequest.setUsername(request.getUsername());
        userServiceRequest.setDateOfBirth(request.getDateOfBirth());

        Player newPlayer;
        try {
            newPlayer = nucleusServiceClient.createPlayer(userServiceRequest);
        } catch (FeignException e) {
            throw feignExceptionHandler.handle(e);
        }

        PlayerAuth playerAuth = new PlayerAuth();
        playerAuth.setPlayerId(newPlayer.getId());
        playerAuth.setEmail(request.getEmail());
        playerAuth.setPasswordHash(passwordEncoder.encode(request.getPassword()));
        playerAuth.setIsEmailVerified(false);

        try {
            playerAuthRepository.save(playerAuth);
        } catch (DataIntegrityViolationException e) {
            throw new DuplicateResourceException("Email already registered");
        } catch (DataAccessException e) {
            throw new DatabaseException("Database error during signup");
        }

        otpService.sendEmailVerificationOtp(newPlayer.getEmail(), newPlayer.getId());
        return newPlayer;
    }
}