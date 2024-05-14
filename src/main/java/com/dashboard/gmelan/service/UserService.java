package com.dashboard.gmelan.service;


import com.dashboard.gmelan.entity.UserEntity;
import com.dashboard.gmelan.entity.enums.UserPermission;
import com.dashboard.gmelan.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserEntity create(String username, String email, String password, String provider) {
        String encryptedPassword = passwordEncoder.encode(password);

        UserEntity user = UserEntity.builder()
                .username(username)
                .password(encryptedPassword)
                .email(email)
                .provider(provider)
                .isAvailable(true)
                .type(UserPermission.USER)
                .build();

        this.userRepository.save(user);

        return user;
    }

    public UserEntity findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public UserEntity findByUserId(long userId) {
        return userRepository.findById(userId);
    }
}
