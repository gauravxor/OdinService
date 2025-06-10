package com.clumsycoder.odinservice.services;

import com.clumsycoder.controlshift.commons.email.EmailService;
import com.clumsycoder.controlshift.commons.enums.OtpPurpose;
import com.clumsycoder.controlshift.commons.enums.OtpType;
import com.clumsycoder.controlshift.commons.generators.OtpGenerator;
import com.clumsycoder.odinservice.dto.request.ValidateOtpRequest;
import com.clumsycoder.odinservice.exception.OtpException;
import com.clumsycoder.odinservice.models.OtpEntity;
import com.clumsycoder.odinservice.models.PlayerAuth;
import com.clumsycoder.odinservice.repositories.OtpRepository;
import com.clumsycoder.odinservice.repositories.PlayerAuthRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class OtpService {
    private final OtpRepository otpRepository;
    private final EmailService emailService;
    private final PlayerAuthRepository playerAuthRepository;

    private String createAndSaveOtp(String email, String playerId, OtpPurpose otpPurpose) {

        PlayerAuth player = playerAuthRepository
                .findByEmail(email)
                .orElseThrow(() -> new OtpException("Player does not exist to save the OTP."));

        OtpEntity otpEntity = new OtpEntity();
        String otpCode = OtpGenerator.generate(OtpType.ALPHANUMERIC);
        LocalDateTime currentTimeStamp = LocalDateTime.now();

        otpEntity.setOtpCode(otpCode);
        otpEntity.setPurpose(otpPurpose);
        otpEntity.setPlayer(player);
        otpEntity.setCreatedAt(currentTimeStamp);
        otpEntity.setExpiresAt(currentTimeStamp.plusSeconds(otpPurpose.getExpirySeconds()));

        otpRepository.save(otpEntity);

        return otpCode;
    }

    @Transactional
    public void sendEmailVerificationOtp(String email, String playerId) {

        OtpPurpose otpPurpose = OtpPurpose.EMAIL_VERIFICATION;
        String generatedOtp = this.createAndSaveOtp(email, playerId, otpPurpose);

        try {
            emailService.sendVerificationOtp(email, generatedOtp);
        } catch (Exception e) {
            throw new OtpException("Failed to send email OTP.");
        }
    }

    @Transactional
    public void validateEmailVerificationOtp(ValidateOtpRequest request) {
        OtpEntity otpEntity = otpRepository.findByPlayer_PlayerIdAndPurpose(
                request.getPlayerId(), OtpPurpose.EMAIL_VERIFICATION
        );

        if (otpEntity == null || !otpEntity.getOtpCode().equals(request.getOtpCode())) {
            throw new OtpException("Invalid OTP.");
        }

        PlayerAuth playerAuth = otpEntity.getPlayer();
        playerAuth.setIsEmailVerified(true);
        playerAuthRepository.save(playerAuth);

        otpRepository.delete(otpEntity);
    }
}