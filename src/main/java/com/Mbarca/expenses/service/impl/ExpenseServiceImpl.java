package com.Mbarca.expenses.service.impl;

import com.Mbarca.expenses.repository.ExpenseRepository;
import com.Mbarca.expenses.dto.request.ExpenseRequestDto;
import com.Mbarca.expenses.domain.Expense;
import com.Mbarca.expenses.service.ExpenseService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ExpenseServiceImpl implements ExpenseService {

    private final ExpenseRepository expenseRepository;

    public ExpenseServiceImpl(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    @Override
    public String createExpense(ExpenseRequestDto expenseRequestDto) {

        Expense expense = mapDtoToExpense(expenseRequestDto);

        Integer createResponse = expenseRepository.createExpense(expense);

        if (createResponse.equals(0)) {
            return "Error al crear el gasto";

        }

        return "Gasto creado correctamente!";
    }

    private Expense mapDtoToExpense(ExpenseRequestDto expenseRequestDto){
        Expense expense = new Expense();
        expense.setAmount(expenseRequestDto.getAmount());
        expense.setCategoryName(expenseRequestDto.getCategoryRequestDto().getName());
        expense.setDate(String.valueOf(LocalDate.now()));
        return expense;
    }
}
