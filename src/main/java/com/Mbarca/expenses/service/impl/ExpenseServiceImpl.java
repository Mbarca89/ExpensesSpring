package com.Mbarca.expenses.service.impl;

import com.Mbarca.expenses.dto.response.ExpenseCategoryResponseDto;
import com.Mbarca.expenses.dto.response.ExpenseResponseDto;
import com.Mbarca.expenses.repository.ExpenseRepository;
import com.Mbarca.expenses.dto.request.ExpenseRequestDto;
import com.Mbarca.expenses.domain.Expense;
import com.Mbarca.expenses.service.ExpenseService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public List<ExpenseResponseDto> getAllExpenses(){
        List<Expense> expenses = expenseRepository.getAllExpenses();
        return expenses.stream().map(this::mapExpenseToExpenseResponseDto).collect(Collectors.toList());
    }

    @Override
    public String deleteExpense(Long id){
        Integer deleteResponse = expenseRepository.deleteExpense(id);
        if (deleteResponse == 0){
            return "Error al eliminar el gasto con id: " + id;
        }
        return "Gasto eliminado correctamente";
    }

    private Expense mapDtoToExpense(ExpenseRequestDto expenseRequestDto){
        Expense expense = new Expense();
        expense.setAmount(expenseRequestDto.getAmount());
        expense.setCategoryName(expenseRequestDto.getCategoryRequestDto().getName());
        expense.setDate(String.valueOf(LocalDate.now()));
        return expense;
    }
    private ExpenseResponseDto mapExpenseToExpenseResponseDto (Expense expense){
        ExpenseResponseDto expenseResponseDto = new ExpenseResponseDto();
        ExpenseCategoryResponseDto expenseCategoryResponseDto = new ExpenseCategoryResponseDto();

        expenseCategoryResponseDto.setName(expense.getCategoryName());

        expenseResponseDto.setId(expense.getId());
        expenseResponseDto.setAmount(expense.getAmount());
        expenseResponseDto.setDate(expense.getDate());
        expenseResponseDto.setExpenseCategoryResponseDto(expenseCategoryResponseDto);

        return expenseResponseDto;
    }
}
