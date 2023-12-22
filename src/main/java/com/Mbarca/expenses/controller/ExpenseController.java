package com.Mbarca.expenses.controller;

import com.Mbarca.expenses.domain.Expense;
import com.Mbarca.expenses.dto.request.ExpenseRequestDto;
import com.Mbarca.expenses.dto.response.ExpenseResponseDto;
import com.Mbarca.expenses.exceptions.BadDataException;
import com.Mbarca.expenses.exceptions.MissingDataException;
import com.Mbarca.expenses.service.ExpenseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
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
        try {
            String response = expenseService.createExpense(expenseRequestDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (MissingDataException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/get")
    public ResponseEntity<List<ExpenseResponseDto>> getAllExpensesHandler() {
        List<ExpenseResponseDto> response = expenseService.getAllExpenses();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ExpenseResponseDto> getExpenseById(@PathVariable String id){
        ExpenseResponseDto response;
        try {
            Long longId = Long.parseLong(id);
            response = expenseService.getExpenseById(longId);
        }catch (NumberFormatException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteExpenseHandler(@PathVariable String id) {
        try {
            Long longId = Long.parseLong(id);
            String response = expenseService.deleteExpense(longId);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (NumberFormatException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Id no válida");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
