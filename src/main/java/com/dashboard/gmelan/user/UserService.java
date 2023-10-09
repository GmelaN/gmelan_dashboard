package com.dashboard.gmelan.user;


import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserEntity create(String username, String email, String password) {
        // verify length
        UserEntity user = new UserEntity();
        user.setUsername(username);
        user.setEmail(email);

        String encryptedPassword = passwordEncoder.encode(password);
        user.setPassword(encryptedPassword);

        this.userRepository.save(user);

        return user;
    }
}
