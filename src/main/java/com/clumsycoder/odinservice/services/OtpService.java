package com.clumsycoder.odinservice.services;

import com.clumsycoder.odinservice.clients.NucleusServiceClient;
import com.clumsycoder.odinservice.dto.request.PlayerUpdateRequest;
import com.clumsycoder.odinservice.models.OtpEntity;
import com.clumsycoder.odinservice.repositories.OtpRepository;
import com.clumsycoder.odinservice.services.exceptions.FeignExceptionHandler;
import com.clumsycoder.controlshift.commons.email.EmailService;
import com.clumsycoder.controlshift.commons.enums.OtpPurpose;
import com.clumsycoder.controlshift.commons.enums.OtpType;
import com.clumsycoder.controlshift.commons.generators.OtpGenerator;
import feign.FeignException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class OtpService {
    private final OtpRepository otpRepository;
    private final EmailService emailService;
    private final NucleusServiceClient nucleusServiceClient;
    private final FeignExceptionHandler feignExceptionHandler;

    private String createAndSaveOtp(String email, String playerId, OtpPurpose otpPurpose) {

        OtpEntity otpEntity = new OtpEntity();
        String otpCode = OtpGenerator.generate(OtpType.ALPHANUMERIC);
        LocalDateTime currentTimeStamp = LocalDateTime.now();

        otpEntity.setOtpCode(otpCode);
        otpEntity.setPurpose(otpPurpose);
        otpEntity.setPlayerId(playerId);
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
            throw new RuntimeException("Failed to send email otp");
        }
    }

    @Transactional
    public boolean validateEmailVerificationOtp(String playerId, String otpCode) {
        Optional<OtpEntity> otpEntityOpt = otpRepository.findByPlayerIdAndOtpCodeAndUsedFalseAndExpiresAtAfter(
                playerId, otpCode, LocalDateTime.now()
        );

        if (otpEntityOpt.isEmpty()) {
            return false;
        }

        OtpEntity otpEntity = otpEntityOpt.get();

        PlayerUpdateRequest player = new PlayerUpdateRequest();
        player.setIsEmailVerified(true);

        try {
            nucleusServiceClient.updatePlayer(playerId, player);
            otpRepository.delete(otpEntity);
            return true;
        } catch (FeignException e) {
            throw feignExceptionHandler.handle(e);
        }
    }
}