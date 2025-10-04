package com.ltw.jwt.service;

import com.ltw.jwt.entity.UserInfo;
import com.ltw.jwt.repository.UserInfoRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserInfoDetailService implements UserDetailsService {

    private final UserInfoRepository repository;

    public UserInfoDetailService(UserInfoRepository repository) {
        this.repository = repository;
    }

    /**
     * Hàm này được Spring Security gọi tự động khi có người login hoặc khi JWTFilter xác thực token.
     * Mục tiêu: tìm user theo username trong database.
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo user = repository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

        // Trả về đối tượng UserDetails mà Spring Security hiểu được
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority(user.getRoles()))
        );
    }
}
