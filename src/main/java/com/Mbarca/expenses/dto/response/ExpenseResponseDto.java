package com.Mbarca.expenses.dto.response;

public class ExpenseResponseDto {
    private Long id;
    private Double amount;
    private String date;
    private ExpenseCategoryResponseDto expenseCategoryResponseDto;

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public ExpenseCategoryResponseDto getExpenseCategoryResponseDto() {
        return expenseCategoryResponseDto;
    }

    public void setExpenseCategoryResponseDto(ExpenseCategoryResponseDto expenseCategoryResponseDto) {
        this.expenseCategoryResponseDto = expenseCategoryResponseDto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    @Override
    public String toString() {
        return "ExpenseResponseDto{" +
                "amount=" + amount +
                ", date='" + date + '\'' +
                ", expenseCategoryResponseDto=" + expenseCategoryResponseDto +
                '}';
    }

}
