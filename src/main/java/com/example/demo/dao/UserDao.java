package com.example.demo.dao;




import com.example.demo.dto.UserRequest;
import com.example.demo.dto.UserResponse;
import com.example.demo.model.User;

public interface UserDao {
	boolean createUser(UserRequest userRequest);
	UserResponse getUserById(long id);
	UserResponse updateUserById(UserRequest userRequest, long id);
	boolean deleteUserById(long id);
	UserResponse login(String email, String password);
	Long getUserIdByEmail(String email);
	UserResponse getUserById(Long userId);
	void save(User user);
	boolean createUser(User user);

}

