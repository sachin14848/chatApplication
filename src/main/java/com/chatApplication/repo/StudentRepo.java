package com.chatApplication.repo;

import com.chatApplication.model.Student;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StudentRepo extends MongoRepository<Student, Long> {
}
