package com.assignment_growthx.assignment_growthx.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assignment_growthx.assignment_growthx.Model.LoginRequest;
import com.assignment_growthx.assignment_growthx.Model.User;
import com.assignment_growthx.assignment_growthx.Repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User registerUser(User user) {
        return userRepository.save(user);
    }

    public boolean authenticate(LoginRequest loginRequest) {
        User user = userRepository.findByUsername(loginRequest.getUsername());
        return user != null && user.getPassword().equals(loginRequest.getPassword());
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public List<User> getUsersByRole(String role) {
        return userRepository.findByRole(role);
    }
}
