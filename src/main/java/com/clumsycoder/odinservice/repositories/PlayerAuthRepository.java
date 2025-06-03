package com.clumsycoder.odinservice.repositories;

import com.clumsycoder.odinservice.models.PlayerAuth;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerAuthRepository extends JpaRepository<PlayerAuth, Long> {

}