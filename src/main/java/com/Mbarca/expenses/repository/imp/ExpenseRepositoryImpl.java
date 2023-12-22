package com.Mbarca.expenses.repository.imp;

import com.Mbarca.expenses.dto.response.ExpenseResponseDto;
import com.Mbarca.expenses.repository.ExpenseRepository;
import com.Mbarca.expenses.domain.Expense;
import com.Mbarca.expenses.domain.ExpenseCategory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

@Repository
public class ExpenseRepositoryImpl implements ExpenseRepository {

    private static final String CREATE_EXPENSE = "INSERT INTO Expense (amount, category_name, category_id, date) VALUES (?, ?, ?, ?)";
    private static final String CREATE_CATEGORY = "INSERT INTO ExpenseCategory (name) SELECT ? WHERE NOT EXISTS (SELECT 1 FROM ExpenseCategory WHERE name = ?)";
    private static final String GET_CATEGORY_BY_NAME = "SELECT * FROM ExpenseCategory WHERE name = ?";
    private static final String GET_ALL_EXPENSES = "SELECT * FROM Expense";
    private static final String GET_EXPENSE_BY_ID = "SELECT * FROM Expense WHERE id = ?";
    private static final String DELETE_EXPENSE = "DELETE FROM Expense WHERE id = ?";
    private static final String DELETE_ALL_EXPENSES_IN_CATEGORY = "DELETE FROM Expense WHERE category_id = ?";
    private final JdbcTemplate jdbcTemplate;

    public ExpenseRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Integer createExpense(Expense expense) {
        jdbcTemplate.update(CREATE_CATEGORY, expense.getCategoryName().toLowerCase(), expense.getCategoryName().toLowerCase());

        Object[] params = {expense.getCategoryName().toLowerCase()};
        int[] types = {1};

        ExpenseCategory expenseCategory = jdbcTemplate.queryForObject(
                GET_CATEGORY_BY_NAME,
                params, types,
                new ExpenseCategoryRowMapper());

        return jdbcTemplate.update(CREATE_EXPENSE,
                expense.getAmount(),
                expenseCategory.getName(),
                expenseCategory.getId(),
                LocalDate.now()
        );
    }

    @Override
    public List<Expense> getAllExpenses(){
        return jdbcTemplate.query(GET_ALL_EXPENSES, new ExpenseRowMapper());
    }

    @Override
    public Expense getExpenseById(Long id){
        Object[] params = {id};
        int[] types = {1};
        return jdbcTemplate.queryForObject(GET_EXPENSE_BY_ID, params, types,new ExpenseRowMapper());
    }

    @Override
    public void updateExpense(int id, String newCategory, Double newAmount){
//        try{
//           PreparedStatement preparedStatement = connection.prepareStatement("UPDATE expense SET category = ?, amount = ? WHERE id = ?");
//           preparedStatement.setString(1, newCategory);
//           preparedStatement.setDouble(2, newAmount);
//           preparedStatement.setInt(3,id);
//
//           int filesAffected = preparedStatement.executeUpdate();
//           if (filesAffected > 0){
//               System.out.println("Actualización correcta");
//           } else {
//               System.out.println("No se encontró el gasto con id " + id);
//           }
//
//           preparedStatement.close();
//        }catch (SQLException e){
//            System.out.println("Error al actualizar el registro: " + e.getMessage());
//        }
    }
    @Override
    public Integer deleteExpense(Long id){
        return jdbcTemplate.update(DELETE_EXPENSE, id);
    }

    @Override
    public void deleteAllExpensesInCategory(Long id){
        jdbcTemplate.update(DELETE_ALL_EXPENSES_IN_CATEGORY, id);
    }

    static class ExpenseCategoryRowMapper implements RowMapper<ExpenseCategory>{
        @Override
        public ExpenseCategory mapRow(ResultSet rs, int rowNum) throws SQLException {
            ExpenseCategory expenseCategory = new ExpenseCategory();
            expenseCategory.setId(rs.getLong("id"));
            expenseCategory.setName(rs.getString("name").toLowerCase());
            return expenseCategory;
        }
    }

    static class ExpenseRowMapper implements RowMapper<Expense>{
        @Override
        public Expense mapRow(ResultSet rs, int rowNum) throws SQLException{
            Expense expense = new Expense();
            expense.setId(rs.getLong("id"));
            expense.setAmount(rs.getDouble("amount"));
            expense.setDate(rs.getString("date"));
            expense.setCategoryName(rs.getString("category_name"));
            expense.setCategoryId(rs.getLong("category_id"));
            return expense;
        }
    }
}