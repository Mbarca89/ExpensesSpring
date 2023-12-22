package com.Mbarca.expenses.service.impl;

import com.Mbarca.expenses.domain.ExpenseCategory;
import com.Mbarca.expenses.dto.request.ExpenseCategoryRequestDto;
import com.Mbarca.expenses.dto.response.ExpenseCategoryResponseDto;
import com.Mbarca.expenses.dto.response.ExpenseResponseDto;
import com.Mbarca.expenses.exceptions.MissingDataException;
import com.Mbarca.expenses.repository.ExpenseCategoryRepository;
import com.Mbarca.expenses.service.ExpenseCategoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
@Service
public class ExpenseCategoryServiceImpl implements ExpenseCategoryService {
    ExpenseCategoryRepository expenseCategoryRepository;

    public ExpenseCategoryServiceImpl(ExpenseCategoryRepository expenseCategoryRepository) {
        this.expenseCategoryRepository = expenseCategoryRepository;
    }


    @Override
    public String createCategory(ExpenseCategoryRequestDto expenseCategoryRequestDto) throws MissingDataException {
        if (expenseCategoryRequestDto.getName() == null ||
                Objects.equals(expenseCategoryRequestDto.getName(), "")){
            throw new MissingDataException("Faltan datos!");
        }
        int response = expenseCategoryRepository.createCategory(expenseCategoryRequestDto);
        if (response == 0) {
            return "Error al crear la categoria!";
        }
        return "Categoria creada correctamente con el nombre: " + expenseCategoryRequestDto.getName();
    }

    @Override
    public String deleteCategory(Long id) {
        int response = expenseCategoryRepository.deleteCategory(id);
        if (response == 0) {
            return "Error al eliminar la categoria!";
        }
        return "Categoria eliminada correctamente!";
    }

    @Override
    public List<ExpenseCategoryResponseDto> getAllCategories() {
       List<ExpenseCategory> categories = expenseCategoryRepository.getAllCategories();
       return categories.stream().map(this::mapExpenseCategoryToExpenseCategoryResponseDto).collect(Collectors.toList());
    }

    private ExpenseCategoryResponseDto mapExpenseCategoryToExpenseCategoryResponseDto(ExpenseCategory expenseCategory){
        ExpenseCategoryResponseDto expenseCategoryResponseDto = new ExpenseCategoryResponseDto();
        expenseCategoryResponseDto.setId(expenseCategory.getId());
        expenseCategoryResponseDto.setName(expenseCategory.getName());
        return expenseCategoryResponseDto;
    }
}


