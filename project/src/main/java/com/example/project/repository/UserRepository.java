package com.example.project.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;
import com.example.project.entity.User;
public interface UserRepository extends JpaRepository<User,Integer>{
  Optional<User>findByEmail(String email);
}