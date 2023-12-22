package com.Mbarca.expenses.controller;

import com.Mbarca.expenses.dto.request.ExpenseCategoryRequestDto;
import com.Mbarca.expenses.dto.response.ExpenseCategoryResponseDto;
import com.Mbarca.expenses.exceptions.MissingDataException;
import com.Mbarca.expenses.service.ExpenseCategoryService;
import com.Mbarca.expenses.service.ExpenseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/expenseCategory")

public class ExpenseCategoryController {
    private final ExpenseCategoryService expenseCategoryService;
    private final ExpenseService expenseService;

    public ExpenseCategoryController(ExpenseCategoryService expenseCategoryService, ExpenseService expenseService) {
        this.expenseCategoryService = expenseCategoryService;
        this.expenseService = expenseService;
    }

    @PostMapping("/create")
    public ResponseEntity<String> createExpenseCategoryHandler(@RequestBody ExpenseCategoryRequestDto expenseCategoryRequestDto){
        try {
        String response = expenseCategoryService.createCategory(expenseCategoryRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (MissingDataException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/get")
    public ResponseEntity<List<ExpenseCategoryResponseDto>> getAllCategoriesHandler(){
        List<ExpenseCategoryResponseDto> response = expenseCategoryService.getAllCategories();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCategoryHandler(@PathVariable String id){
        try {
            Long longId = Long.parseLong(id);
            expenseService.deleteAllExpensesInCategory(longId);
            String response = expenseCategoryService.deleteCategory(longId);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }catch (NumberFormatException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
