package com.example.project.exception;

public class ExpenseNotFoundException extends RuntimeException{
  public ExpenseNotFoundException(String msg)
  {
    super(msg);
  }
}
