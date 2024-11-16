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

    @Autowired
    private AssignmentRepository assignmentRepository;

    @Autowired
    private UserRepository userRepository;

    public Assignment uploadAssignment(Assignment assignment) {
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

    public List<Assignment> getAssignmentsByAdmin(String adminUsername) {
        return assignmentRepository.findByAdmin(adminUsername);
    }

    public List<Assignment> getAssignmentsByUserId(String userId) {
        return assignmentRepository.findByUserId(userId);
    }
    
}
