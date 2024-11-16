package com.assignment_growthx.assignment_growthx.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.assignment_growthx.assignment_growthx.Model.Assignment;
import com.assignment_growthx.assignment_growthx.Model.LoginRequest;
import com.assignment_growthx.assignment_growthx.Model.LoginResponse;
import com.assignment_growthx.assignment_growthx.Model.User;
import com.assignment_growthx.assignment_growthx.Service.AssignmentService;
import com.assignment_growthx.assignment_growthx.Service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {


    @Autowired
    private UserService userService;

    // Register a new user/admin
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        User registeredUser = userService.registerUser(user);
        return ResponseEntity.ok(registeredUser);
    }

    // Login for both user and admin
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        boolean isAuthenticated = userService.authenticate(loginRequest);
        if (isAuthenticated) {
            User user = userService.getUserByUsername(loginRequest.getUsername());
            return ResponseEntity.ok(new LoginResponse("Login successful", user.getUsername(), user.getRole()));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }
    }

  // Fetch all admins
  @GetMapping("/admins")
  public ResponseEntity<List<User>> getAllAdmins() {
      List<User> admins = userService.getUsersByRole("ADMIN");
      return ResponseEntity.ok(admins);
  }
  
    @Autowired
    private AssignmentService assignmentService;

    @PostMapping("/upload")
    public ResponseEntity<?> uploadAssignment(@RequestBody Assignment assignment) {
        try {
            Assignment savedAssignment = assignmentService.uploadAssignment(assignment);
            return ResponseEntity.ok(savedAssignment);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/assignments")
public ResponseEntity<?> getUserAssignments(@RequestParam String userId) {
    try {
        List<Assignment> assignments = assignmentService.getAssignmentsByUserId(userId);
        if (assignments.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No assignments found for this user.");
        }
        return ResponseEntity.ok(assignments);
    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
    }
}

}