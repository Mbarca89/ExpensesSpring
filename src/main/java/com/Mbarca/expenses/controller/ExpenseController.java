package com.Mbarca.expenses.controller;

import com.Mbarca.expenses.domain.Expense;
import com.Mbarca.expenses.dto.request.ExpenseRequestDto;
import com.Mbarca.expenses.dto.response.ExpenseResponseDto;
import com.Mbarca.expenses.service.ExpenseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/expense")
public class ExpenseController {

    private final ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @PostMapping("/create")
    public ResponseEntity<String> createExpenseHandler(@RequestBody ExpenseRequestDto expenseRequestDto) {
        String response = expenseService.createExpense(expenseRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/get")
    public ResponseEntity<List<ExpenseResponseDto>> getAllExpensesHandler() {
        List<ExpenseResponseDto> response = expenseService.getAllExpenses();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteExpense (@PathVariable Long id) {
        String response = expenseService.deleteExpense(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
