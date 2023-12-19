package com.Mbarca.expenses.domain;

public class Expense {
    private Double amount;
    private String categoryName;
    private String date;

    public Expense() {
    }

    public Expense(Double amount, String categoryName, String date) {
        this.amount = amount;
        this.categoryName = categoryName;
        this.date = date;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Expense{" +
                ", amount=" + amount +
                ", category=" + categoryName +
                ", date='" + date + '\'' +
                '}';
    }


}
