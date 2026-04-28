package com.github.fellyipe.assettrackapi.controller;

import com.github.fellyipe.assettrackapi.domain.model.User;
import com.github.fellyipe.assettrackapi.domain.repository.UserRepository;
import com.github.fellyipe.assettrackapi.dto.LoginDTO;
import com.github.fellyipe.assettrackapi.security.JwtService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserRepository userRepository;

    public AuthController(AuthenticationManager authenticationManager,
                          JwtService jwtService,
                          UserRepository userRepository) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.userRepository = userRepository;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody @Valid LoginDTO dto) {
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                dto.email(),
                dto.password()
            )
        );

        User user = userRepository.findByEmail(dto.email())
            .orElseThrow(() -> new RuntimeException("User not found"));


        String token = jwtService.generateToken(user);

        return ResponseEntity.ok(token);
    }

    @GetMapping("/test")
    public String test() {
        return "ok";
    }

}
