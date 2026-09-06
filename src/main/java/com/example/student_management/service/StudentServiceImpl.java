package com.example.student_management.service;

import com.example.student_management.dto.StudentRequestDTO;
import com.example.student_management.dto.StudentResponseDTO;
import com.example.student_management.entity.Student;
import com.example.student_management.exception.StudentNotFoundException;
import com.example.student_management.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;


    public StudentServiceImpl(StudentRepository studentRepository) {

        this.studentRepository = studentRepository;
    }

    @Override
    public List<StudentResponseDTO> getAllStudents() {

        return studentRepository.findAll()
                .stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public StudentResponseDTO getStudentById(String id) {

        Student student = studentRepository.findById(id)
                .orElseThrow(() ->
                        new StudentNotFoundException(
                                "Student not found with id: " + id
                        )
                );

        return convertToResponseDTO(student);
    }

    @Override
    public List<StudentResponseDTO> getStudentsByName(
            String name) {

        return studentRepository
                .findByNameIgnoreCase(name)
                .stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public StudentResponseDTO createStudent(
            StudentRequestDTO requestDTO) {

        Student student = convertToEntity(requestDTO);

        Student savedStudent =
                studentRepository.save(student);

        return convertToResponseDTO(savedStudent);
    }

    @Override
    public StudentResponseDTO updateStudent(
            String id,
            StudentRequestDTO requestDTO) {

        Student student = studentRepository.findById(id)
                .orElseThrow(() ->
                        new StudentNotFoundException(
                                "Student not found with id: " + id
                        )
                );

        student.setName(requestDTO.getName());
        student.setEmail(requestDTO.getEmail());
        student.setAge(requestDTO.getAge());
        student.setCourse(requestDTO.getCourse());

        Student updatedStudent =
                studentRepository.save(student);

        return convertToResponseDTO(updatedStudent);
    }

    @Override
    public boolean deleteStudent(String id) {

        if (!studentRepository.existsById(id)) {
            throw new StudentNotFoundException(
                    "Student not found with id: " + id
            );
        }

        studentRepository.deleteById(id);

        return true;
    }

    private Student convertToEntity(
            StudentRequestDTO requestDTO) {

        Student student = new Student();

        student.setName(requestDTO.getName());
        student.setEmail(requestDTO.getEmail());
        student.setAge(requestDTO.getAge());
        student.setCourse(requestDTO.getCourse());

        return student;
    }

    private StudentResponseDTO convertToResponseDTO(
            Student student) {

        return new StudentResponseDTO(
                student.getId(),
                student.getName(),
                student.getEmail(),
                student.getAge(),
                student.getCourse()
        );
    }
}