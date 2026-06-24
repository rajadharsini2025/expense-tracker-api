package com.example.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.project.dto.CategorySummaryDTO;
import com.example.project.entity.Expense;
import com.example.project.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;


public interface ExpenseRepository extends JpaRepository<Expense,Integer> {
     List<Expense>findByCategory(String category);
     List<Expense>findByAmountGreaterThan(double amount);
     List<Expense>findByUser(User user);
     @Query("SELECT SUM(e.amount) FROM Expense e")
     Double getTotalExpense();
     @Query("""
               SELECT SUM(e.amount) FROM Expense e
               WHERE e.user=:user
               """)
     Double getTotalExpenseByUser(@Param("user")User user);
     @Query("""
               SELECT SUM(e.amount) FROM Expense e
               WHERE e.user=:user AND e.category=:category
               """)
     Double getTotalExpenseByUserAndCategory(@Param("user")User user, @Param("category")String category);
     @Query("""
               SELECT SUM(e.amount) FROM Expense e 
               WHERE e.user=:user AND MONTH(e.date)=:month
               """)
     Double getMonthlyExpenseTotal(@Param("user")User user, @Param("month")int month);
     @Query("""
               SELECT new com.example.project.dto.CategorySummaryDTO(e.category,SUM(e.amount))
               FROM Expense e WHERE e.user=:user GROUP BY e.category
               """)
     List<CategorySummaryDTO>getCategorySummary(@Param("user")User user);
     @Query("""
               SELECT new com.example.project.dto.CategorySummaryDTO(e.category,SUM(e.amount))
               FROM Expense e WHERE e.user=:user GROUP BY e.category ORDER BY SUM(e.amount) DESC
               """)
     List<CategorySummaryDTO>getCategoriesOrderedByTotal(@Param("user")User user);     
} 
