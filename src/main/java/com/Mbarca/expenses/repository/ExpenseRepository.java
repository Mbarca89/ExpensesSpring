package com.Mbarca.expenses.repository;

import com.Mbarca.expenses.domain.Expense;

import java.sql.ResultSet;

public interface ExpenseRepository {

    Integer createExpense(Expense expense);
    void getExpenses();
    ResultSet getExpenseById(int id);
    void updateExpense(int id, String newCategory, Double newAmount);
    void deleteExpense (int id);
}
