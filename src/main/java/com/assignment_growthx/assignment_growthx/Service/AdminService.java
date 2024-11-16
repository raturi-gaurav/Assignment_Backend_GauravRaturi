package com.assignment_growthx.assignment_growthx.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assignment_growthx.assignment_growthx.Model.Assignment;
import com.assignment_growthx.assignment_growthx.Repository.AssignmentRepository;

@Service
public class AdminService {

    // Injecting the AssignmentRepository to perform database operations

    @Autowired
    private AssignmentRepository assignmentRepository;

    public List<Assignment> getAssignmentsForAdmin(String admin) {
        return assignmentRepository.findByAdmin(admin);
    }

    //Updates the status of a specific assignment by its ID.

    public Assignment updateAssignmentStatus(String id, String status) {
        Assignment assignment = assignmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Assignment not found with ID: " + id));

        // Update the status of the assignment
        assignment.setStatus(status);
        // Save the updated assignment back to the database and return it
        return assignmentRepository.save(assignment);
    }
}
