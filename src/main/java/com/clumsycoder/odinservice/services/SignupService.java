package com.clumsycoder.odinservice.services;

import com.clumsycoder.odinservice.clients.NucleusServiceFeignClient;
import com.clumsycoder.odinservice.dto.Player;
import com.clumsycoder.odinservice.dto.internal.CreatePlayerRequest;
import com.clumsycoder.odinservice.dto.request.SignupRequest;
import com.clumsycoder.odinservice.exception.OdinServiceException;
import com.clumsycoder.odinservice.models.PlayerAuth;
import com.clumsycoder.odinservice.repositories.PlayerAuthRepository;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SignupService {
    private final OtpService otpService;
    private final NucleusServiceFeignClient nucleusServiceFeignClient;
    private final PlayerAuthRepository playerAuthRepository;
    private final PasswordEncoder passwordEncoder;

    public Player signUp(SignupRequest request) {
        CreatePlayerRequest userServiceRequest = new CreatePlayerRequest();
        userServiceRequest.setEmail(request.getEmail());
        userServiceRequest.setFirstName(request.getFirstName());
        userServiceRequest.setLastName(request.getLastName());
        userServiceRequest.setUsername(request.getUsername());
        userServiceRequest.setDateOfBirth(request.getDateOfBirth());

        Player newPlayer;
        newPlayer = nucleusServiceFeignClient.createPlayer(userServiceRequest);

        PlayerAuth playerAuth = new PlayerAuth();
        playerAuth.setPlayerId(newPlayer.getId());
        playerAuth.setEmail(request.getEmail());
        playerAuth.setPasswordHash(passwordEncoder.encode(request.getPassword()));
        playerAuth.setIsEmailVerified(false);

        try {
            playerAuthRepository.save(playerAuth);
        } catch (DataIntegrityViolationException e) {
            throw new OdinServiceException("Email already registered", e);
        } catch (DataAccessException e) {
            throw new OdinServiceException("Unexpected error signing up the user.");
        }

        otpService.sendEmailVerificationOtp(newPlayer.getEmail(), newPlayer.getId());
        return newPlayer;
    }
}