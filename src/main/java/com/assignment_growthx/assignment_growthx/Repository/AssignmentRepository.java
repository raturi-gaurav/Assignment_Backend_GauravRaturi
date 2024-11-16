package com.assignment_growthx.assignment_growthx.Repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.assignment_growthx.assignment_growthx.Model.Assignment;


//Repository interface for performing CRUD operations on the Assignment collection in MongoDB.
@Repository
public interface AssignmentRepository extends MongoRepository<Assignment, String> {


    List<Assignment> findByAdmin(String adminUsername);


    //Method to find assignments associated with a specific user ID.

    List<Assignment> findByUserId(String userId);

}
