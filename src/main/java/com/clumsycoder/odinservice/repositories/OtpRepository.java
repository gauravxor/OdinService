package com.clumsycoder.odinservice.repositories;

import com.clumsycoder.controlshift.commons.enums.OtpPurpose;
import com.clumsycoder.odinservice.models.OtpEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Optional;

public interface OtpRepository extends JpaRepository<OtpEntity, Long> {
    OtpEntity findByPlayer_PlayerIdAndPurpose(
            String playerId, OtpPurpose purpose
    );

}