package com.Mbarca.expenses.repository;

import com.Mbarca.expenses.domain.Expense;
import com.Mbarca.expenses.dto.response.ExpenseResponseDto;

import java.sql.ResultSet;
import java.util.List;

public interface ExpenseRepository {

    Integer createExpense(Expense expense);
    List<Expense> getAllExpenses();
    Expense getExpenseById(Long id);
    void updateExpense(int id, String newCategory, Double newAmount);
    Integer deleteExpense (Long id);
}
