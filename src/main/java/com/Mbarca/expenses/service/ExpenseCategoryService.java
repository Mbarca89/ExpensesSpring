package com.Mbarca.expenses.service;

import com.Mbarca.expenses.domain.ExpenseCategory;
import com.Mbarca.expenses.dto.request.ExpenseCategoryRequestDto;
import com.Mbarca.expenses.dto.response.ExpenseCategoryResponseDto;
import com.Mbarca.expenses.exceptions.MissingDataException;

import java.util.List;

public interface ExpenseCategoryService {
    String createCategory(ExpenseCategoryRequestDto expenseCategoryRequestDto) throws MissingDataException;
    String deleteCategory(Long id);
    List<ExpenseCategoryResponseDto> getAllCategories();
}
