package com.clumsycoder.odinservice.services;

import com.clumsycoder.odinservice.clients.NucleusServiceFeignClient;
import com.clumsycoder.odinservice.dto.Player;
import com.clumsycoder.odinservice.dto.request.LoginRequest;
import com.clumsycoder.odinservice.exception.auth.InvalidCredentialsException;
import com.clumsycoder.odinservice.exception.user.UserNotFoundException;
import com.clumsycoder.odinservice.models.PlayerAuth;
import com.clumsycoder.odinservice.repositories.PlayerAuthRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LoginService {
    private final NucleusServiceFeignClient nucleusServiceFeignClient;
    private final PasswordEncoder passwordEncoder;
    private final PlayerAuthRepository playerAuthRepository;

    public Player login(LoginRequest request) {
        PlayerAuth playerAuth = playerAuthRepository
                .findByEmail((request.getEmail()))
                .orElseThrow(UserNotFoundException::new);

        String hashedPassword = playerAuth.getPasswordHash();
        String password = request.getPassword();
        if (!passwordEncoder.matches(password, hashedPassword)) {
            throw new InvalidCredentialsException();
        }

        return nucleusServiceFeignClient.getPlayerById(playerAuth.getPlayerId());
    }
}