package com.example.student_management.service;


import com.example.student_management.dto.*;

import java.util.List;

public interface StudentService {

    List<StudentResponseDTO> getAllStudents();

    StudentResponseDTO getStudentById(String id);

    List<StudentResponseDTO> getStudentsByName(String name);

    StudentResponseDTO createStudent(StudentRequestDTO requestDTO);

    StudentResponseDTO updateStudent(
            String id,
            StudentRequestDTO requestDTO);

    boolean deleteStudent(String id);
}