package com.example.project.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.project.dto.CategorySummaryDTO;
import com.example.project.dto.ExpenseRequestDTO;
import com.example.project.entity.Expense;
import com.example.project.service.ExpenseService;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.DeleteMapping;
import java.util.*;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;
@RestController
public class ExpenseController {
  private final ExpenseService expenseService;
  public ExpenseController(ExpenseService expenseService)
  {
    this.expenseService=expenseService;
  }
  @PostMapping("/expense")
  public ResponseEntity<Expense>addExpense(@Valid @RequestBody ExpenseRequestDTO dto) { 
      Expense expense=expenseService.convertToEntity(dto);     
      return ResponseEntity.status(201).body(expenseService.addExpense(expense));
  }
  @GetMapping("/expenses")
  public ResponseEntity<Page<Expense>>getAllExpenses(@RequestParam int page, @RequestParam int size) {
      return ResponseEntity.ok(expenseService.getAllExpenses(page,size));
  }
  @GetMapping("/expense/{id}")
  public ResponseEntity<Expense> getExpenseById(@PathVariable int id) {
      return ResponseEntity.ok(expenseService.getExpenseById(id));
  }
  @GetMapping("/expenses/category/{category}")
  public ResponseEntity<List<Expense>> getExpenseByCategory(@PathVariable String category) {
      return ResponseEntity.ok(expenseService.getExpenseByCategory(category));
  }
  @GetMapping("/expenses/amount")
  public ResponseEntity<List<Expense>> getExpenseByAmount(@RequestParam double amount) {
      return ResponseEntity.ok(expenseService.getExpenseByAmount(amount));
  }
  @GetMapping("/expenses/sort")
  public ResponseEntity<List<Expense>>getExpenseSorted(@RequestParam String field)
  {
    return ResponseEntity.ok(expenseService.getSortedExpense(field));
  }
  @GetMapping("/my-expenses")
  public ResponseEntity<List<Expense>>getMyExpenses()
  {
    return ResponseEntity.ok(expenseService.getMyExpenses());
  }
  @GetMapping("/expenses/total")
  public ResponseEntity<Double>getTotalExpenses()
  {
    return ResponseEntity.ok(expenseService.getTotalExpense());
  }
  @PutMapping("/expense/{id}")
  public ResponseEntity<Expense> updateExpense(@PathVariable int id,@Valid @RequestBody ExpenseRequestDTO dto) {
    Expense expense=expenseService.convertToEntity(dto);     
    return ResponseEntity.ok(expenseService.updateExpense(id, expense));
  }
  @GetMapping("/my-expenses/total")
  public ResponseEntity<Double>getMyTotalExpense() {
      return ResponseEntity.ok(expenseService.getMyTotalExpense());
  }
  @GetMapping("/my-expenses/category-summary")
  public ResponseEntity<List<CategorySummaryDTO>>getCategorySummary() {
      return ResponseEntity.ok(expenseService.getCategorySummary());
  }
  @GetMapping("/my-expenses/top-category")
  public ResponseEntity<CategorySummaryDTO>getTopCategory() {
      return ResponseEntity.ok(expenseService.getTopCategory());
  }
  @GetMapping("/my-expenses/category/{category}/total")
  public ResponseEntity<Double>getCategoryTotal(@PathVariable String category) {
      return ResponseEntity.ok(expenseService.getMyCategoryTotal(category));
  }  
  @GetMapping("/my-expenses/month/{month}/total")
  public ResponseEntity<Double>getMonthlyTotal(@PathVariable int month) {
      return ResponseEntity.ok(expenseService.getMyMonthlyExpenseTotal(month));
  }
  @DeleteMapping("/expense/{id}")
  public ResponseEntity<String> deleteExpense(@PathVariable int id)
  {
      expenseService.deleteExpense(id);
      return ResponseEntity.ok("Expense deleted successfully");
  }
  
}
