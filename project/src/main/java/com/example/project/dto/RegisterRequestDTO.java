package com.example.project.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class RegisterRequestDTO {
  @NotBlank(message = "Name cannot be blank")
  private String name;
  @Email(message = "Enter valid email address")
  private String email;
  @NotBlank(message = "Password cannot be blank")
  private String password;
  public void setName(String name)
  {
    this.name=name;
  }
  public String getName()
  {
    return name;
  }
  public void setEmail(String email)
  {
    this.email=email;
  }
  public String getEmail()
  {
    return email;
  }
  public void setPassword(String password)
  {
    this.password=password;
  }
  public String getPassword()
  {
    return password;
  }  
}
