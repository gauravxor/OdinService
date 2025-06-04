package com.clumsycoder.odinservice.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.Instant;

@Entity
@Table
@Data
public class PlayerAuth {
    @Id
    @Column(name = "player_id")
    private String playerId;

    @Column
    private String email;

    @Column
    private String passwordHash;

    @Column
    private Boolean isEmailVerified;

    @Column
    private Instant createdAt;

    @Column
    private Instant updatedAt;

    @PrePersist
    public void prePersist() {
        Instant currentTime = Instant.now();
        if (this.createdAt == null) this.createdAt = currentTime;
        if (this.updatedAt == null) this.updatedAt = currentTime;
    }

    @PreUpdate
    public void preUpdate() {
        updatedAt = Instant.now();
    }
}