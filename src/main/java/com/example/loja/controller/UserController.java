package com.example.loja.controller;

import com.example.loja.models.UserModels;
import com.example.loja.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/auth")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("user")
    public ResponseEntity<List<UserModels>> buscarUser(){
        return ResponseEntity.status(HttpStatus.OK).body(userRepository.findAll());
    }



}
