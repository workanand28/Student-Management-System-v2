package com.example.student_management.service;

import com.example.student_management.model.Student;

import java.util.List;

public interface StudentService {

    List<Student> getAllStudents();

    Student getStudentById(int id);

    List<Student> getStudentsByName(String name);

    Student createStudent(Student student);

    Student updateStudent(int id, Student updatedStudent);

    boolean deleteStudent(int id);
}