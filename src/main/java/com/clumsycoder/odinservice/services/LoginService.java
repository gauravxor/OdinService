package com.clumsycoder.odinservice.services;

import com.clumsycoder.odinservice.clients.NucleusServiceClient;
import com.clumsycoder.odinservice.dto.common.Player;
import com.clumsycoder.odinservice.dto.internal.PlayerAuthResponse;
import com.clumsycoder.odinservice.dto.request.PlayerLoginRequest;
import com.clumsycoder.odinservice.services.exceptions.FeignExceptionHandler;
import com.clumsycoder.controlshift.commons.exceptions.UnauthorizedException;
import feign.FeignException;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LoginService {
    private final NucleusServiceClient nucleusServiceClient;
    private final PasswordEncoder passwordEncoder;
    private final FeignExceptionHandler feignExceptionHandler;

    public Player login(PlayerLoginRequest request) {
        try {

            // this would return the
            PlayerAuthResponse playerAuth = nucleusServiceClient.getPlayerAuthDataByEmail(request.getEmail());

            String rawPassword = request.getPassword();
            String encodedPassword = playerAuth.getPassword();

            if (passwordEncoder.matches(rawPassword, encodedPassword)) {
                return null;
//                        playerAuth.getEmail(),
//                        playerAuth.getId(), "true"
////                        playerAuth.getIsEmailVerified()
//                );
            }
            throw new UnauthorizedException("Invalid password provided");

        } catch (FeignException e) {
            throw feignExceptionHandler.handle(e);
        }
    }
}