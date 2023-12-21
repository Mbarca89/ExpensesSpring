package com.Mbarca.expenses;

import com.Mbarca.expenses.controller.ExpenseController;
import com.Mbarca.expenses.dto.request.ExpenseCategoryRequestDto;
import com.Mbarca.expenses.dto.request.ExpenseRequestDto;
import com.Mbarca.expenses.exceptions.MissingDataException;
import com.Mbarca.expenses.service.ExpenseService;
import jdk.jfr.Description;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

class ExpenseControllerTest {

    ExpenseService expenseService = mock(ExpenseService.class);
    ExpenseController expenseController = new ExpenseController(expenseService);

    @Description("Create expense")
    @Test
    public void shouldInsertValidExpense() throws MissingDataException {

        // GIVEN
        ExpenseRequestDto expenseRequestDto = new ExpenseRequestDto();
        ExpenseCategoryRequestDto expenseCategoryRequestDto = new ExpenseCategoryRequestDto();

        expenseCategoryRequestDto.setName("prueba");

        expenseRequestDto.setAmount(2000.00);
        expenseRequestDto.setCategoryRequestDto(expenseCategoryRequestDto);

        String serviceResponse = "Gasto creado correctamente!";

        //WHEN
        when(expenseService.createExpense(expenseRequestDto)).thenReturn(serviceResponse);

        ResponseEntity<String> controllerResponse = expenseController.createExpenseHandler(expenseRequestDto);

        //THEN
        assertEquals(HttpStatus.CREATED, controllerResponse.getStatusCode());
        assertEquals(serviceResponse, controllerResponse.getBody());
        verify(expenseService, times(1)).createExpense(expenseRequestDto);

    }

    @Test
    public void shouldNotInsertInvalidExpense() throws MissingDataException {

        // GIVEN
        ExpenseRequestDto expenseRequestDto = new ExpenseRequestDto();

        String serviceResponse = "Faltan datos!";

        // WHEN
        Mockito.doThrow(new MissingDataException("Faltan datos!")).when(expenseService).createExpense(any(ExpenseRequestDto.class));

        ResponseEntity<String> controllerResponse = expenseController.createExpenseHandler(expenseRequestDto);

        // THEN
        assertEquals(HttpStatus.BAD_REQUEST, controllerResponse.getStatusCode());
        assertEquals(serviceResponse, controllerResponse.getBody());
        verify(expenseService, times(1)).createExpense(expenseRequestDto);
    }

    @Description("Delete expense")
    @Test
    void shouldDeleteExpenseWhenValidId() {
        //GIVEN

        String id = "1";

        //WHEN
        when(expenseService.deleteExpense(anyLong())).thenReturn("Gasto eliminado correctamente");

        ResponseEntity<String> response = expenseController.deleteExpenseHandler(id);

        //THEN
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Gasto eliminado correctamente", response.getBody());
        verify(expenseService, times(1)).deleteExpense(1L);
    }

    @Test
    void shouldNotDeleteWhenInvalidId() {
        //GIVEN

        String id = "invalid id";

        //WHEN
        when(expenseService.deleteExpense(anyLong())).thenReturn("Id no válida");

        ResponseEntity<String> response = expenseController.deleteExpenseHandler(id);

        //THEN
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Id no válida", response.getBody());
        verify(expenseService, times(0)).deleteExpense(1L);
    }
}