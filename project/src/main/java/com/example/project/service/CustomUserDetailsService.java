package com.example.project.service;
import org.springframework.stereotype.Service;

import com.example.project.entity.User;
import com.example.project.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
@Service
public class CustomUserDetailsService implements UserDetailsService
{
   private final UserRepository userRepository;
   CustomUserDetailsService(UserRepository userRepository)
   {
    this.userRepository=userRepository;
   }
   @Override
   public UserDetails loadUserByUsername(String email)
   {
      User user=userRepository.findByEmail(email).orElseThrow(()->new UsernameNotFoundException("User not found"));
      return org.springframework.security.core.userdetails.User
                .builder().username(user.getEmail()).password(user.getPassword())
                .authorities("USER").build();
   }
}