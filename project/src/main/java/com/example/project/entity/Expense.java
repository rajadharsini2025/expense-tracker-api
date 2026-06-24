package com.example.project.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.time.LocalDate;
import jakarta.validation.constraints.*;

@Entity
public class Expense {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  @ManyToOne
  @JoinColumn(name="user_id")
  private User user;
  @NotBlank(message = "Category Cannot be empty")
  private String category;
  @NotBlank(message = "Description cannot be empty")
  private String description;
  @Positive(message = "Amount must be greater than zero")
  private double amount;
  @NotNull(message = "Date cannot be null")
  private LocalDate date;
  public int getId()
  {
      return id;
  }
  public void setId(int id)
  {
    this.id=id;
  }
  public void setCategory(String category)
  {
    this.category=category;
  }
  public String getCategory()
  {
    return category;
  }
  public void setDescription(String description)
  {
    this.description=description;
  }
  public User getUser()
  {
    return user;
  }
  public void setUser(User user)
  {
    this.user=user;
  }
  public String getDescription()
  {
    return description;
  }
  public void setAmount(double amount)
  {
    this.amount=amount;
  }
  public double getAmount()
  {
    return amount;
  }
  public void setDate(LocalDate date)
  {
    this.date=date;
  }
  public LocalDate getDate()
  {
    return date;
  }
}
