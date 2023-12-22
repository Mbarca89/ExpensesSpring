package com.Mbarca.expenses.repository.imp;

import com.Mbarca.expenses.domain.ExpenseCategory;
import com.Mbarca.expenses.dto.request.ExpenseCategoryRequestDto;
import com.Mbarca.expenses.repository.ExpenseCategoryRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
@Repository
public class ExpenseCategoryRepositoryImpl implements ExpenseCategoryRepository {

    private static final String CREATE_CATEGORY = "INSERT INTO ExpenseCategory (name) SELECT ? WHERE NOT EXISTS (SELECT 1 FROM ExpenseCategory WHERE name = ?)";
    private static final String DELETE_CATEGORY = "DELETE FROM ExpenseCategory WHERE id = ?";
    private static final String GET_ALL_CATEGORIES = "SELECT * FROM ExpenseCategory";

    private final JdbcTemplate jdbcTemplate;

    public ExpenseCategoryRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Integer createCategory(ExpenseCategoryRequestDto expenseCategoryRequestDto) {
        return jdbcTemplate.update(CREATE_CATEGORY, expenseCategoryRequestDto.getName(), expenseCategoryRequestDto.getName());

    }

    @Override
    public Integer deleteCategory(Long id) {

        return jdbcTemplate.update(DELETE_CATEGORY, id);
    }

    @Override
    public List<ExpenseCategory> getAllCategories() {
        return jdbcTemplate.query(GET_ALL_CATEGORIES, new ExpenseCategoryRowMapper());
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
}
