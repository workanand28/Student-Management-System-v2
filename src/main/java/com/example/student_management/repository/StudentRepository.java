package com.example.student_management.repository;

import com.example.student_management.model.Student;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface StudentRepository
        extends MongoRepository<Student, String> {

    List<Student> findByNameIgnoreCase(String name);
}