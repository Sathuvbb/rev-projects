//package com.example.demo.dao;
//
//
//
//import com.example.demo.dao.CartDao;
//import com.example.demo.dto.CartRequest;
//import com.example.demo.dto.CartResponse;
//
//import com.example.demo.model.CartItem;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.jdbc.core.RowMapper;
//import org.springframework.stereotype.Repository;
//
//import javax.sql.DataSource;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.List;
//
//@Repository
//public class CartDaoimpl implements CartDao {
//	 private final JdbcTemplate jdbcTemplate;
//
//	    @Autowired
//	    public CartDaoImpl(DataSource dataSource) {
//	        this.jdbcTemplate = new JdbcTemplate(dataSource);
//	    }
//
//	    @Override
//	    public boolean addCart(CartRequest cartRequest) {
//	        String sql = "INSERT INTO carts (id, product_id, quantity) VALUES (?, ?, ?)";
//	        try {
//	            jdbcTemplate.update(sql, cartRequest.getId(), cartRequest.getProductId(), cartRequest.getQuantity());
//	            return true;
//	        } catch (Exception e) {
//	            e.printStackTrace(); // Log the exception or handle it as needed
//	            return false; // Indicate that the operation was not successful
//	        }
//	    }
//
//	    @Override
//	    public CartResponse getCartById(int id) {
//	        String sql = "SELECT * FROM carts WHERE id = ?";
//	        try {
//	            return jdbcTemplate.queryForObject(sql, new Object[]{id}, new CartRowMapper());
//	        } catch (Exception e) {
//	            e.printStackTrace(); // Log the exception or handle it as needed
//	            return null; // Indicate that the cart was not found or an error occurred
//	        }
//	    }
//
//	    @Override
//	    public boolean updateCart(CartRequest cartRequest) {
//	        String sql = "UPDATE carts SET product_id = ?, quantity = ? WHERE id = ?";
//	        try {
//	            jdbcTemplate.update(sql, cartRequest.getProductId(), cartRequest.getQuantity(), cartRequest.getId());
//	            return true;
//	        } catch (Exception e) {
//	            e.printStackTrace(); // Log the exception or handle it as needed
//	            return false; // Indicate that the operation was not successful
//	        }
//	    }
//
//	    @Override
//	    public boolean deleteCartById(int id) {
//	        String sql = "DELETE FROM carts WHERE id = ?";
//	        try {
//	            jdbcTemplate.update(sql, id);
//	            return true;
//	        } catch (Exception e) {
//	            e.printStackTrace(); // Log the exception or handle it as needed
//	            return false; // Indicate that the operation was not successful
//	        }
//	    }
//
//	    @Override
//	    public List<CartResponse> getAllCarts() {
//	        String sql = "SELECT * FROM carts";
//	        try {
//	            return jdbcTemplate.query(sql, new CartRowMapper());
//	        } catch (Exception e) {
//	            e.printStackTrace(); // Log the exception or handle it as needed
//	            return null; // Indicate that the operation was not successful
//	        }
//	    }
//
//	    @Override
//	    public void addToCart(Integer userId, int productId) {
//	        // Implementation depends on business logic
//	        // For example: Handle adding a product to the cart for a specific user
//	    }
//
//	    @Override
//	    public void addToCart(int userId, int productId, int quantity) {
//	        // Implementation depends on business logic
//	        // For example: Handle adding a product with quantity to the cart for a specific user
//	    }
//
//	    private static class CartRowMapper implements RowMapper<CartResponse> {
//	        @Override
//	        public CartResponse mapRow(ResultSet rs, int rowNum) throws SQLException {
//	            return new CartResponse(
//	                rs.getLong("id"),
//	                rs.getLong("product_id"),
//	                rs.getString("product_name"),  // Assuming product_name is in the table
//	                rs.getInt("quantity"),
//	                rs.getDouble("price")           // Assuming price is in the table
//	            );
//	        }
//	    }
//	}