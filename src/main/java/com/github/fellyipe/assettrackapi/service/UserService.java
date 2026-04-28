package com.github.fellyipe.assettrackapi.service;

import com.github.fellyipe.assettrackapi.domain.model.Role;
import com.github.fellyipe.assettrackapi.domain.model.User;
import com.github.fellyipe.assettrackapi.domain.repository.UserRepository;
import com.github.fellyipe.assettrackapi.dto.CreateUserDTO;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User create(CreateUserDTO dto) {
        System.out.println("Criando usuário...");
        User user = new User();
        user.setName(dto.name());
        user.setEmail(dto.email());
        user.setPassword(passwordEncoder.encode(dto.password()));
        user.setRole(dto.role());

        return userRepository.save(user);
    }
}
