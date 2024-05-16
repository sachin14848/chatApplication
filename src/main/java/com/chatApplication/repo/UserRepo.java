package com.chatApplication.repo;

import com.chatApplication.model.Status;
import com.chatApplication.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UserRepo extends MongoRepository<User, String> {
    List<User> findAllByStatus(Status status);
}
