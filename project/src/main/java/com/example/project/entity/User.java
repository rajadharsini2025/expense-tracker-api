package com.example.project.entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.util.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity
public class User {
  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private int id;
  @NotBlank(message="Name cannot be blank")
  private String name;
  @OneToMany(mappedBy = "user")
  @JsonIgnore
  private List<Expense> expenses;
  @Email(message="Please enter valid email address")
  private String email;
  @NotBlank(message="Password cannot be blank")
  private String password;
  public int getId()
  {
    return id;
  }
  public  void setId(int id)
  {
    this.id=id;
  }
  public List<Expense> getExpenses()
  {
    return expenses;
  }
  public void setExpenses(List<Expense>expenses)
  {
    this.expenses=expenses;
  }
  public String getName()
  {
    return name;
  }
  public void setName(String name)
  {
    this.name=name;
  }
  public String getEmail()
  {
    return email;
  }
  public void setEmail(String email)
  {
    this.email=email;
  }
  public String getPassword()
  {
    return password;
  }
  public void setPassword(String password)
  {
    this.password=password;
  }
}
