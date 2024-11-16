package com.assignment_growthx.assignment_growthx.Service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assignment_growthx.assignment_growthx.Model.Assignment;
import com.assignment_growthx.assignment_growthx.Model.User;
import com.assignment_growthx.assignment_growthx.Repository.AssignmentRepository;
import com.assignment_growthx.assignment_growthx.Repository.UserRepository;

@Service
public class AssignmentService {

    // Injecting AssignmentRepository for assignment-related database operations
    @Autowired
    private AssignmentRepository assignmentRepository;

    // Injecting UserRepository for user-related database operations
    @Autowired
    private UserRepository userRepository;

    public Assignment uploadAssignment(Assignment assignment) {
        // Validate the user associated with the assignmen
        User user = userRepository.findByUsername(assignment.getUserId());
        if (user == null || !"USER".equals(user.getRole())) {
            throw new RuntimeException("User not found or invalid role.");
        }

        User admin = userRepository.findByUsername(assignment.getAdmin());
        if (admin == null || !"ADMIN".equals(admin.getRole())) {
            throw new RuntimeException("Admin not found or invalid role.");
        }

        assignment.setStatus("PENDING");
        assignment.setCreatedAt(new Date());

        return assignmentRepository.save(assignment);
    }
    //Retrieves a list of assignments associated with a specific admin.
    public List<Assignment> getAssignmentsByAdmin(String adminUsername) {
        return assignmentRepository.findByAdmin(adminUsername);
    }

    //Retrieves a list of assignments associated with a specific user.

    public List<Assignment> getAssignmentsByUserId(String userId) {
        return assignmentRepository.findByUserId(userId);
    }
    
}
