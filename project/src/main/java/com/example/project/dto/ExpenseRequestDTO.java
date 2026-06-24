package com.example.project.dto;
import jakarta.validation.constraints.*;
import java.time.LocalDate;

public class ExpenseRequestDTO {
  @NotBlank(message = "Category Cannot be empty")
  private String category;
  @NotBlank(message = "Description cannot be empty")
  private String description;
  @Positive(message = "Amount must be greater than zero")
  private double amount;
  @NotNull(message = "Date cannot be null")
  private LocalDate date;
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
