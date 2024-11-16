package com.assignment_growthx.assignment_growthx.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.assignment_growthx.assignment_growthx.Model.Assignment;
import com.assignment_growthx.assignment_growthx.Service.AdminService;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    // Update the status of an assignment
    @PostMapping("/assignments/{id}/{action}")
    public ResponseEntity<?> updateAssignmentStatus(@PathVariable String id, @PathVariable String action) {
        try {
            String status = action.equalsIgnoreCase("accept") ? "ACCEPTED" : "REJECTED";
            Assignment updatedAssignment = adminService.updateAssignmentStatus(id, status);
            return ResponseEntity.ok(updatedAssignment);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    // Fetch all assignments assigned to an admin
    @GetMapping("/assignments")
    public ResponseEntity<?> getAssignments(@RequestParam String adminUsername) {
        List<Assignment> assignments = adminService.getAssignmentsForAdmin(adminUsername);
        if (assignments.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No assignments found for this admin.");
        }
        return ResponseEntity.ok(assignments);
    }
}
