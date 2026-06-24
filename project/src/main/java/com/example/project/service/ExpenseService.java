package com.example.project.service;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import java.util.*;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.example.project.dto.CategorySummaryDTO;
import com.example.project.dto.ExpenseRequestDTO;
import com.example.project.entity.Expense;
import com.example.project.entity.User;
import com.example.project.exception.ExpenseNotFoundException;
import com.example.project.exception.UnauthorizedAccessException;
import com.example.project.exception.UserNotFoundException;
import com.example.project.repository.ExpenseRepository;
import com.example.project.repository.UserRepository;
@Service
public class ExpenseService
{
    private final ExpenseRepository expenseRespository;
    private final UserRepository userRepository;
    public ExpenseService(ExpenseRepository expenseRepository,UserRepository userRepository)
    {
      this.expenseRespository=expenseRepository;
      this.userRepository=userRepository;
    }

    public Expense addExpense(Expense expense)
    {
      String email=getLoggedInUserEmail();
      User user=userRepository.findByEmail(email).orElseThrow(()->new UserNotFoundException("User cannot be found"));
      expense.setUser(user);
      return expenseRespository.save(expense);
    }
    public Page<Expense> getAllExpenses(int page,int size)
    {
      Pageable pageable=PageRequest.of(page,size);
      return expenseRespository.findAll(pageable);
    }
    public Expense getExpenseById(int id)
    {
      return expenseRespository.findById(id).orElseThrow(()->new ExpenseNotFoundException("The Expense not found withid"+id));
    }
    public List<Expense> getExpenseByCategory(String category)
    {
      return expenseRespository.findByCategory(category);
    }
    public List<Expense>getExpenseByAmount(double amount)
    {
      return expenseRespository.findByAmountGreaterThan(amount);
    }
    public List<Expense>getSortedExpense(String sortingData)
    {
       return expenseRespository.findAll(Sort.by(sortingData)); 
    }
    public Double getTotalExpense()
    {
      return expenseRespository.getTotalExpense();
    }
    public String getLoggedInUserEmail()
    {
      Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
      return authentication.getName();
    }
    public List<Expense>getMyExpenses()
    {
      String email=getLoggedInUserEmail();
      User user=userRepository.findByEmail(email).orElseThrow(()->new UserNotFoundException("User not found"));
      return expenseRespository.findByUser(user);
    }
    public Expense updateExpense(int id, Expense expense)
    {
      Expense temp=expenseRespository.findById(id).orElseThrow(()->new ExpenseNotFoundException("The Expense not found withid"+id));
      User user=getLoggedInUser();
      if(expense.getUser().getId()!=user.getId())
      {
        throw new UnauthorizedAccessException("You cannot update another person's expense");
      }
      temp.setAmount(expense.getAmount());
      temp.setCategory(expense.getCategory());
      temp.setDescription(expense.getDescription());
      temp.setDate(expense.getDate());
      return expenseRespository.save(temp);
    }
    public void deleteExpense(int id)
    {
      Expense expense = expenseRespository.findById(id).orElseThrow(() ->new ExpenseNotFoundException("Expense not found with id " + id));
      User user=getLoggedInUser();
      if(expense.getUser().getId()!=user.getId())
      {
        throw new UnauthorizedAccessException("You cannot delete another person's expense");
      }
      expenseRespository.delete(expense);
    }
    public Expense convertToEntity(ExpenseRequestDTO dto)
    {
      Expense expense=new Expense();
      expense.setAmount(dto.getAmount());
      expense.setCategory(dto.getCategory());
      expense.setDate(dto.getDate());
      expense.setDescription(dto.getDescription());
      return expense;
    }
    public Double getMyTotalExpense()
    {
      String email=getLoggedInUserEmail();
      User user=userRepository.findByEmail(email).orElseThrow(()->new UserNotFoundException("User not found"));
      Double total=expenseRespository.getTotalExpenseByUser(user);
      return total==null?0.0:total;
    }
    public Double getMyCategoryTotal(String category)
    {
      String email=getLoggedInUserEmail();
      User user=userRepository.findByEmail(email).orElseThrow(()->new UserNotFoundException("User not found"));
      Double total=expenseRespository.getTotalExpenseByUserAndCategory(user,category);
      return total==null?0.0:total;
    }
    public Double getMyMonthlyExpenseTotal(int month)
    {
      String email=getLoggedInUserEmail();
      User user=userRepository.findByEmail(email).orElseThrow(()->new UserNotFoundException("User not found"));
      Double total=expenseRespository.getMonthlyExpenseTotal(user,month);
      return total==null?0.0:total;
    }
    public List<CategorySummaryDTO>getCategorySummary()
    {
      String email=getLoggedInUserEmail();
      User user=userRepository.findByEmail(email).orElseThrow(()->new UserNotFoundException("User not found"));
      return expenseRespository.getCategorySummary(user);
    }
    public CategorySummaryDTO getTopCategory()
    {
      String email=getLoggedInUserEmail();
      User user=userRepository.findByEmail(email).orElseThrow(()->new UserNotFoundException("User not found"));
      List<CategorySummaryDTO> categories=expenseRespository.getCategoriesOrderedByTotal(user);
      if(categories.isEmpty())
        return null;
      return categories.get(0);
    }
    private User getLoggedInUser()
    {
      String email=SecurityContextHolder.getContext().getAuthentication().getName();
      return userRepository.findByEmail(email).orElseThrow(()->new UserNotFoundException("User not found"));
    }
}
