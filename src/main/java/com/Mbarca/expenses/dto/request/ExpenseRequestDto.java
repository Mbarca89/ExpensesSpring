package com.Mbarca.expenses.dto.request;

public class ExpenseRequestDto {
    private Double amount;
    private ExpenseCategoryRequestDto categoryRequestDto;

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public ExpenseCategoryRequestDto getCategoryRequestDto() {
        return categoryRequestDto;
    }

    public void setCategoryRequestDto(ExpenseCategoryRequestDto categoryRequestDto) {
        this.categoryRequestDto = categoryRequestDto;
    }

}
