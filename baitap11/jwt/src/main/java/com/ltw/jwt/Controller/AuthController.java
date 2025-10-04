package com.ltw.jwt.Controller;

import com.ltw.jwt.entity.UserInfo;
import com.ltw.jwt.repository.UserInfoRepository;
import com.ltw.jwt.service.JwtService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserInfoRepository repo;
    private final PasswordEncoder encoder;
    private final JwtService jwtService;

    public AuthController(UserInfoRepository repo, PasswordEncoder encoder, JwtService jwtService) {
        this.repo = repo;
        this.encoder = encoder;
        this.jwtService = jwtService;
    }

    @PostMapping("/register")
    public String register(@RequestBody UserInfo user) {
        user.setPassword(encoder.encode(user.getPassword()));
        repo.save(user);
        return "User registered!";
    }

    @PostMapping("/login")
    public String login(@RequestBody UserInfo user) {
        var dbUser = repo.findByUsername(user.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));
        if (encoder.matches(user.getPassword(), dbUser.getPassword())) {
            return jwtService.generateToken(user.getUsername());
        } else {
            throw new RuntimeException("Invalid credentials");
        }
    }
}
