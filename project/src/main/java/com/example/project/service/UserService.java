package com.example.project.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.project.entity.User;
import com.example.project.exception.InvalidCredentialsException;
import com.example.project.dto.LoginRequestDTO;
import com.example.project.dto.RegisterRequestDTO;
import com.example.project.dto.UserResponseDTO;
import com.example.project.repository.UserRepository;
import com.example.project.util.JwtUtil;

@Service
public class UserService {
  private final UserRepository userRepository;
  private final BCryptPasswordEncoder passwordEncoder;
  private final JwtUtil jwtUtil;
  public UserService(UserRepository userRepository,BCryptPasswordEncoder passwordEncoder,JwtUtil jwtUtil)
  {
    this.jwtUtil=jwtUtil;
    this.userRepository=userRepository;
    this.passwordEncoder=passwordEncoder;
  }
  public UserResponseDTO registerUser(RegisterRequestDTO dto)
  {
      User user=new User();
      user.setEmail(dto.getEmail());
      user.setName(dto.getName());
      user.setPassword(passwordEncoder.encode(dto.getPassword()));
      User savedUser = userRepository.save(user);
      return new UserResponseDTO(savedUser.getId(),savedUser.getName(),savedUser.getEmail());
  }
  public String loginUser(LoginRequestDTO dto)
  {
    User user=userRepository.findByEmail(dto.getEmail()).orElseThrow(()->new InvalidCredentialsException("Invalid email"));
    if(!passwordEncoder.matches(dto.getPassword(),user.getPassword()))
    {
      throw new InvalidCredentialsException("Invalid password");
    }
    return jwtUtil.generateToken(user.getEmail());
  }
}
