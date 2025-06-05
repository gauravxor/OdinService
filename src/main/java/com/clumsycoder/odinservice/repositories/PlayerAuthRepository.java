package com.clumsycoder.odinservice.repositories;

import com.clumsycoder.odinservice.models.PlayerAuth;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlayerAuthRepository extends JpaRepository<PlayerAuth, String> {

    Optional<PlayerAuth> findByEmail(String email);

}