package com.github.fellyipe.assettrackapi.domain.repository;

import com.github.fellyipe.assettrackapi.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByEmail(String email);
}
