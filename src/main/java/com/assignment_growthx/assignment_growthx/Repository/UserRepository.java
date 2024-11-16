package com.assignment_growthx.assignment_growthx.Repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.assignment_growthx.assignment_growthx.Model.User;

/**
 * Repository interface for performing CRUD operations on the User collection in MongoDB.
 * Extends the MongoRepository interface to provide built-in database interaction methods.
 */
@Repository
public interface UserRepository extends MongoRepository<User, String> {
    User findByUsername(String username);
    List<User> findByRole(String role);
}
