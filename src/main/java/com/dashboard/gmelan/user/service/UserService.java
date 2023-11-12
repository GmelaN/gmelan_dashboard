package com.dashboard.gmelan.user.service;


import com.dashboard.gmelan.user.Entity.UserEntity;
import com.dashboard.gmelan.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserEntity create(String username, String email, String password, String type) {
        // verify length
        UserEntity user = new UserEntity();
        user.setUsername(username);
        user.setEmail(email);
        user.setType(type);
        user.setCreatedAt(new Timestamp(20200101));

//        String encryptedPassword = passwordEncoder.encode(password);
//        user.setPassword(encryptedPassword);
        user.setPassword(password);

        this.userRepository.save(user);

        return user;
    }

    public UserEntity findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
    public UserEntity findByUserId(long userId) { return userRepository.findById(userId); }
}
