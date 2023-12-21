package com.Mbarca.expenses.service;

import com.Mbarca.expenses.domain.Expense;
import com.Mbarca.expenses.dto.request.ExpenseRequestDto;
import com.Mbarca.expenses.dto.response.ExpenseResponseDto;
import com.Mbarca.expenses.exceptions.BadDataException;
import com.Mbarca.expenses.exceptions.MissingDataException;

import java.util.List;

public interface ExpenseService {
    String createExpense (ExpenseRequestDto expenseRequestDto) throws MissingDataException;
    List<ExpenseResponseDto> getAllExpenses ();
    Expense getExpenseById(Long id);
    String deleteExpense (Long id);
}
