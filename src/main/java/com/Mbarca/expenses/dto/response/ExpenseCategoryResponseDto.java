package com.Mbarca.expenses.dto.response;

public class ExpenseCategoryResponseDto {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ExpenseCategoryResponseDto{" +
                "name='" + name + '\'' +
                '}';
    }
}
