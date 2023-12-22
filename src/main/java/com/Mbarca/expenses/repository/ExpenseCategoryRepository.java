package com.Mbarca.expenses.repository;

import com.Mbarca.expenses.domain.ExpenseCategory;
import com.Mbarca.expenses.dto.request.ExpenseCategoryRequestDto;

import java.util.List;

public interface ExpenseCategoryRepository {
    Integer createCategory(ExpenseCategoryRequestDto expenseCategoryRequestDto);
    Integer deleteCategory(Long id);
    List<ExpenseCategory> getAllCategories();
}
