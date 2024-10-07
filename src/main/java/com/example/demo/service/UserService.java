package com.example.demo.service;

import com.example.demo.dao.UserDao;
import com.example.demo.dao.UserDaoimp;
import com.example.demo.dto.UserRequest;
import com.example.demo.dto.UserResponse;
import com.example.demo.model.Role;
import com.example.demo.model.User;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private UserDao userDao;

    // Register a new user with encrypted password
    @Transactional
    public boolean registerUser(UserRequest userRequest) {
    	  // Check if email already exists
        Long existingUserId = userDao.getUserIdByEmail(userRequest.getEmail());
        if (existingUserId != null) {
            // Email already exists
            return false;
        }
        if (userRequest.getRole() == null) {
            userRequest.setRole(Role.USER);
        }

        return userDao.createUser(userRequest);
    }
    
    public UserResponse getUserById(long id) {
        User user = entityManager.find(User.class, id);
        if (user != null) {
            return new UserResponse(user.getId(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getRole());
        }
        return null; 
    }

    @Transactional
    public UserResponse updateUserById(UserRequest userRequest, long id) {
        User user = entityManager.find(User.class, id);
        if (user != null) {
            user.setFirstName(userRequest.getFirstName());
            user.setLastName(userRequest.getLastName());
            user.setEmail(userRequest.getEmail());
            user.setPassword(userRequest.getPassword());  // Update password if needed (add hashing)
            user.setRole(userRequest.getRole());  // Update user role
            entityManager.merge(user);
            return new UserResponse(user.getId(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getRole());
        }
        return null;  // Handle cases where user is not found
    }

    @Transactional
    public boolean deleteUserById(long id) {
        User user = entityManager.find(User.class, id);
        if (user != null) {
            entityManager.remove(user);
            return true;
        }
        return false;  // Handle cases where user is not found
    }

    public UserResponse login(String email, String password) {
        try {
            String query = "SELECT u FROM User u WHERE u.email = :email AND u.password = :password";
            User user = entityManager.createQuery(query, User.class)
                                     .setParameter("email", email)
                                     .setParameter("password", password)  // Password should be hashed and validated
                                     .getSingleResult();
            return new UserResponse(user.getId(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getRole());
        } catch (Exception e) {
            // Log this error
            System.out.println("Login failed: " + e.getMessage());
            return null;
        }
    }

    public boolean validateUser(String email, String password) {
        UserResponse userResponse = login(email, password);
        return userResponse != null;
    }

    public String validateUserAndGetRole(String email, String password) {
        try {
            // Query to fetch user by email and password
            String query = "SELECT u FROM User u WHERE u.email = :email AND u.password = :password";
            User user = entityManager.createQuery(query, User.class)
                                     .setParameter("email", email)
                                     .setParameter("password", password)  // Consider hashing the password
                                     .getSingleResult();

            // Return the user's role if found
            if (user != null) {
                return user.getRole().toString(); // Convert enum to String
            }
        } catch (Exception e) {
            // Handle exceptions (e.g., no result found)
            System.out.println("Login failed: " + e.getMessage());  // Use logging here
        }
        return null;  // Return null if user not found or exception occurs
    }

    // Admin validation logic
    public boolean validateAdmin(String email, String password) {
        try {
            String query = "SELECT u FROM User u WHERE u.email = :email AND u.password = :password AND u.role = 'ADMIN'";
            User user = entityManager.createQuery(query, User.class)
                                     .setParameter("email", email)
                                     .setParameter("password", password)  // Password validation with hashing can be added
                                     .getSingleResult();
            return user != null;
        } catch (Exception e) {
            // Handle exception (e.g., no result found)
            System.out.println("Admin login failed: " + e.getMessage());  // Log the exception
            return false;
        }
    }

	public boolean createUser(UserRequest userRequest) {
		// TODO Auto-generated method stub
		return false;
	}
}
