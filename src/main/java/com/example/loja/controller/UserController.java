package com.example.loja.controller;

import com.example.loja.config.JwtUtil;
import com.example.loja.dto.UserLoginDto;
import com.example.loja.dto.UserRegisterDto;
import com.example.loja.models.UserModels;
import com.example.loja.repository.UserRepository;
import com.example.loja.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/auth")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtUtil jwtUtil;
    @Autowired
    private UserService userService;



    @GetMapping("user")
    public ResponseEntity<List<UserModels>> buscarUser(){
        return ResponseEntity.status(HttpStatus.OK).body(userRepository.findAll());
    }


    public void AuthController(UserService userService, AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("register")
    public ResponseEntity<String> register(@Valid @RequestBody UserRegisterDto userRegisterDTO) {



        UserModels user = new UserModels();
        user.setUsername(userRegisterDTO.username());
        user.setPassword(new BCryptPasswordEncoder().encode(userRegisterDTO.password()));
        user.setRole("ROLE_USER");
        userService.register(user);
        return ResponseEntity.ok("User registered successfully!");
    }

    @PostMapping("login")
    public ResponseEntity<?> login(@RequestBody UserLoginDto userLoginDTO) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userLoginDTO.username(), userLoginDTO.password()));
            String token = jwtUtil.generateToken(userLoginDTO.username());
            return ResponseEntity.ok(token);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials!");
        }
    }

}
