package com.assignment_growthx.assignment_growthx.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assignment_growthx.assignment_growthx.Model.Assignment;
import com.assignment_growthx.assignment_growthx.Repository.AssignmentRepository;

@Service
public class AdminService {

    @Autowired
    private AssignmentRepository assignmentRepository;

    public List<Assignment> getAssignmentsForAdmin(String admin) {
        return assignmentRepository.findByAdmin(admin);
    }

    public Assignment updateAssignmentStatus(String id, String status) {
        Assignment assignment = assignmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Assignment not found with ID: " + id));

        assignment.setStatus(status);
        return assignmentRepository.save(assignment);
    }
}
