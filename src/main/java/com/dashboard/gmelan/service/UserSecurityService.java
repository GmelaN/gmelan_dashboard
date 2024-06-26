package com.dashboard.gmelan.service;

import com.dashboard.gmelan.entity.UserEntity;
import com.dashboard.gmelan.repository.UserRepository;
import com.dashboard.gmelan.config.roles.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserSecurityService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity users = this.userRepository.findByUsername(username);

        if(users == null) {
            throw new UsernameNotFoundException("사용자를 찾을 수 없습니다.");
        }

//        UserEntity user = users.get();
        UserEntity user = users;

        List<GrantedAuthority> authorities = new ArrayList<>();
        if("admin".equals(username)) {
            authorities.add(new SimpleGrantedAuthority(UserRole.ADMIN.getValue()));
        } else if("default".equals(username)) {
            throw new UsernameNotFoundException("사용자를 찾을 수 없습니다.");
        } else {
            authorities.add(new SimpleGrantedAuthority(UserRole.USER.getValue()));
        }

        return new User(user.getUsername(), user.getPassword(), authorities);
    }
}
