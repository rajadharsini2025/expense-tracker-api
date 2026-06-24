package com.example.project.dto;

public class CategorySummaryDTO {
  private String category;
  private Double total;
  public CategorySummaryDTO(String category, Double total)
  {
    this.total=total;
    this.category=category;
  }
  public Double getTotal()
  {
     return total;
  }
  public String getCategory()
  {
    return category;
  }
}
