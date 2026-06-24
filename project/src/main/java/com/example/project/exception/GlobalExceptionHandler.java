package com.example.project.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
  @ExceptionHandler(ExpenseNotFoundException.class)
  public ResponseEntity<String>handleExceptionNotFound(ExpenseNotFoundException ex)
  {
    return ResponseEntity.status(404).body(ex.getMessage());
  }
  @ExceptionHandler(UnauthorizedAccessException.class)
  public ResponseEntity<String>handleUnauthorizedAccess(UnauthorizedAccessException ex)
  {
    return ResponseEntity.status(403).body(ex.getMessage());
  }
  @ExceptionHandler(UserNotFoundException.class)
  public ResponseEntity<String>handleUserNotFound(UserNotFoundException ex)        
  {
      return ResponseEntity.status(404).body(ex.getMessage());
  }
  @ExceptionHandler(InvalidCredentialsException.class)
  public ResponseEntity<String>handleInvalidCredentials(InvalidCredentialsException ex)
  {
      return ResponseEntity.status(401).body(ex.getMessage());
  }
  }
