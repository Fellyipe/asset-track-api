package com.github.fellyipe.assettrackapi.controller;

import com.github.fellyipe.assettrackapi.domain.model.User;
import com.github.fellyipe.assettrackapi.dto.CreateUserDTO;
import com.github.fellyipe.assettrackapi.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<User> create(@RequestBody @Valid CreateUserDTO dto) {
        System.out.println("Criando usuário...");
        User user = userService.create(dto);
        return ResponseEntity.ok(user);
    }

}
