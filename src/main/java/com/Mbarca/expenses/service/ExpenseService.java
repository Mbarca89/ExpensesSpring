package com.Mbarca.expenses.service;

import com.Mbarca.expenses.dto.request.ExpenseRequestDto;

public interface ExpenseService {
    String createExpense (ExpenseRequestDto expenseRequestDto);
}
