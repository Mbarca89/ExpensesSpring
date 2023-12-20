package com.Mbarca.expenses.service;

import com.Mbarca.expenses.domain.Expense;
import com.Mbarca.expenses.dto.request.ExpenseRequestDto;
import com.Mbarca.expenses.dto.response.ExpenseResponseDto;

import java.util.List;

public interface ExpenseService {
    String createExpense (ExpenseRequestDto expenseRequestDto);
    List<ExpenseResponseDto> getAllExpenses ();
    String deleteExpense (Long id);
}
