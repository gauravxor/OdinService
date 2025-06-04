package com.clumsycoder.odinservice.repositories;

import com.clumsycoder.odinservice.models.OtpEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Optional;

public interface OtpRepository extends JpaRepository<OtpEntity, Long> {
    Optional<OtpEntity> findByPlayer_PlayerIdAndOtpCodeAndUsedFalseAndExpiresAtAfter(
            String playerId,
            String otpCode,
            LocalDateTime currentTime
    );

}