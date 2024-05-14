package com.dashboard.gmelan.repository;

import com.dashboard.gmelan.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByUsername(String username);

    UserEntity findById(long userId);

}
