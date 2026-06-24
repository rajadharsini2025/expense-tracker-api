package com.example.project.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.example.project.entity.User;
import com.example.project.service.UserService;
import com.example.project.dto.LoginRequestDTO;
import com.example.project.dto.RegisterRequestDTO;
import com.example.project.dto.UserResponseDTO;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;



@RestController
public class UserController {
  private final UserService userService;
  public UserController(UserService userService)
  {
    this.userService=userService;
  }
  @PostMapping("/register")
  public ResponseEntity<UserResponseDTO>registerUser(@Valid @RequestBody RegisterRequestDTO dto)
  {
    return ResponseEntity.status(201).body(userService.registerUser(dto));
  }
  @PostMapping("/login")
  public ResponseEntity<String>loginUser(@Valid @RequestBody LoginRequestDTO dto) {
      return ResponseEntity.ok(userService.loginUser(dto));
  }
  

}
