package com.Mbarca.expenses.repository.imp;

import com.Mbarca.expenses.repository.ExpenseRepository;
import com.Mbarca.expenses.domain.Expense;
import com.Mbarca.expenses.domain.ExpenseCategory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

@Repository
public class ExpenseRepositoryImpl implements ExpenseRepository {

    private static final String CREATE_EXPENSE = "INSERT INTO Expense (amount, category_name, category_id, date) VALUES (?, ?, ?, ?)";
    private static final String CREATE_CATEGORY = "INSERT INTO ExpenseCategory (name) SELECT ? WHERE NOT EXISTS (SELECT 1 FROM ExpenseCategory WHERE name = ?)";
    private static final String GET_CATEGORY_BY_NAME = "SELECT * FROM ExpenseCategory WHERE name = ?";
    private final JdbcTemplate jdbcTemplate;

    public ExpenseRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Integer createExpense(Expense expense) {
        jdbcTemplate.update(CREATE_CATEGORY, expense.getCategoryName().toLowerCase(), expense.getCategoryName().toLowerCase());

        Object[] params = {expense.getCategoryName()};
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

    static class ExpenseCategoryRowMapper implements RowMapper<ExpenseCategory>{
        @Override
        public ExpenseCategory mapRow(ResultSet rs, int rowNum) throws SQLException {
            ExpenseCategory expenseCategory = new ExpenseCategory();
            expenseCategory.setId(rs.getLong("id"));
            expenseCategory.setName(rs.getString("name"));
            return expenseCategory;
        }
    }

    @Override
    public void getExpenses(){
//        try{
//            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM expense");
//            ResultSet rs = preparedStatement.executeQuery();
//            while (rs.next()){
//                System.out.println("Gasto Nº " + rs.getInt("id"));
//                System.out.println(("Monto: " + rs.getDouble("amount")));
//                System.out.println("Categoria: " + rs.getString("category"));
//                System.out.println("Fecha: " + rs.getString("date_added"));
//                System.out.println("-----------------------------------------------------");
//            }
//            rs.close();
//            preparedStatement.close();
//        }catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
    }

    @Override
    public ResultSet getExpenseById(int id){
//        ResultSet rs;
//        try{
//            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM expense WHERE id = ?");
//            preparedStatement.setInt(1,id);
//            rs = preparedStatement.executeQuery();
//        }catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        return rs;
        return null;
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
    public void deleteExpense(int id){
//        try {
//            PreparedStatement preparedStatement = connection.prepareStatement("DELETE expense WHERE id = ?");
//            preparedStatement.setInt(1, id);
//
//            int filesAffected = preparedStatement.executeUpdate();
//            if (filesAffected > 0){
//                System.out.println("Eliminado correctamente!");
//            } else {
//                System.out.println("No se encontró el gasto con id " + id);
//            }
//
//            preparedStatement.close();
//        } catch (SQLException e) {
//            System.out.println("Error al eliminar el registro: " + e.getMessage());
//        }
//

    }
}