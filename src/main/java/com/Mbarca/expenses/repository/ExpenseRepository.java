package com.Mbarca.expenses.repository;

import com.Mbarca.expenses.domain.Expense;

import java.sql.ResultSet;
import java.util.List;

public interface ExpenseRepository {

    Integer createExpense(Expense expense);
    List<Expense> getAllExpenses();
    ResultSet getExpenseById(int id);
    void updateExpense(int id, String newCategory, Double newAmount);
    Integer deleteExpense (Long id);
}
