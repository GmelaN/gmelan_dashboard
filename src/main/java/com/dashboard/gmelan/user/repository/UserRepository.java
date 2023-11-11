package com.dashboard.gmelan.user.repository;

import com.dashboard.gmelan.user.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByusername(String username);
}
