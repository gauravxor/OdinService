package com.clumsycoder.odinservice.services;

import com.clumsycoder.odinservice.clients.NucleusServiceClient;
import com.clumsycoder.odinservice.dto.common.Player;
import com.clumsycoder.odinservice.dto.internal.CreatePlayerRequest;
import com.clumsycoder.odinservice.dto.request.PlayerSignupRequest;
import com.clumsycoder.odinservice.models.PlayerAuth;
import com.clumsycoder.odinservice.repositories.PlayerAuthRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class  SignupService {
    private final OtpService otpService;
    private final NucleusServiceClient nucleusServiceClient;
    private final PlayerAuthRepository playerAuthRepository;
    private final PasswordEncoder passwordEncoder;

    public Player createPlayer(PlayerSignupRequest request) {
        CreatePlayerRequest  userServiceRequest = new CreatePlayerRequest();
        userServiceRequest.setEmail(request.getEmail());
        userServiceRequest.setFirstName(request.getFirstName());
        userServiceRequest.setLastName(request.getLastName());
        userServiceRequest.setUsername(request.getUsername());
        userServiceRequest.setDateOfBirth(request.getDateOfBirth());

        Player newPlayer = nucleusServiceClient.createPlayer(userServiceRequest);

        PlayerAuth playerAuth = new PlayerAuth();
        playerAuth.setPlayerId(newPlayer.getId());
        playerAuth.setEmail(request.getEmail());
        playerAuth.setPasswordHash(passwordEncoder.encode(request.getPassword()));
        playerAuth.setIsEmailVerified(false);
        playerAuthRepository.save(playerAuth);

        otpService.sendEmailVerificationOtp(newPlayer.getEmail(), newPlayer.getId());
        return newPlayer;
    }
}