package com.Mbarca.expenses.service.impl;

import com.Mbarca.expenses.controller.ExpenseController;
import com.Mbarca.expenses.dto.request.ExpenseCategoryRequestDto;
import com.Mbarca.expenses.dto.request.ExpenseRequestDto;
import com.Mbarca.expenses.exceptions.MissingDataException;
import com.Mbarca.expenses.repository.ExpenseRepository;
import com.Mbarca.expenses.service.ExpenseService;
import jdk.jfr.Description;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


class ExpenseServiceImplTest {
    ExpenseRepository expenseRepository = mock(ExpenseRepository.class);
    ExpenseService expenseService = new ExpenseServiceImpl(expenseRepository);
    @Description("create expense")
    @Test
    public void shouldInsertValidExpense() throws MissingDataException {
        //GIVEN
        ExpenseRequestDto expenseRequestDto = new ExpenseRequestDto();
        ExpenseCategoryRequestDto expenseCategoryRequestDto = new ExpenseCategoryRequestDto();

        expenseCategoryRequestDto.setName("Test");

        expenseRequestDto.setAmount(2000.00);
        expenseRequestDto.setCategoryRequestDto(expenseCategoryRequestDto);

        //WHEN
        when(expenseRepository.createExpense(any())).thenReturn(1);
        String result = expenseService.createExpense(expenseRequestDto);

        //THEN
        verify(expenseRepository, times(1)).createExpense(any());
        assertEquals("Gasto creado correctamente!", result);
    }

    @Test
    public void shouldGetMissingDataException () {
        //GIVEN
        ExpenseRequestDto expenseRequestDto = new ExpenseRequestDto();
        //THEN
        assertThrows(MissingDataException.class, () -> expenseService.createExpense(expenseRequestDto));
        verify(expenseRepository, never()).createExpense(any());

    }
}