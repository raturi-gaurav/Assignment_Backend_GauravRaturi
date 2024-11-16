package com.assignment_growthx.assignment_growthx.Repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.assignment_growthx.assignment_growthx.Model.Assignment;

@Repository
public interface AssignmentRepository extends MongoRepository<Assignment, String> {
    List<Assignment> findByAdmin(String adminUsername);

    List<Assignment> findByUserId(String userId);

}
