package com.example.student_management.controller;

import com.example.student_management.dto.StudentRequestDTO;
import com.example.student_management.dto.StudentResponseDTO;
import com.example.student_management.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(
            StudentService studentService) {

        this.studentService = studentService;
    }

    @GetMapping
    public ResponseEntity<List<StudentResponseDTO>>
    getAllStudents() {

        return ResponseEntity.ok(
                studentService.getAllStudents()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentResponseDTO>
    getStudentById(
            @PathVariable String id) {

        StudentResponseDTO student =
                studentService.getStudentById(id);

        if (student == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(student);
    }

    @GetMapping("/search")
    public ResponseEntity<List<StudentResponseDTO>>
    getStudentsByName(
            @RequestParam String name) {

        return ResponseEntity.ok(
                studentService.getStudentsByName(name)
        );
    }

    @PostMapping
    public ResponseEntity<StudentResponseDTO>
    createStudent(
            @Valid @RequestBody StudentRequestDTO requestDTO) {

        StudentResponseDTO savedStudent =
                studentService.createStudent(requestDTO);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(savedStudent);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentResponseDTO>
    updateStudent(
            @PathVariable String id,
            @Valid @RequestBody StudentRequestDTO requestDTO) {

        StudentResponseDTO updatedStudent =
                studentService.updateStudent(
                        id,
                        requestDTO
                );

        if (updatedStudent == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(updatedStudent);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(
            @PathVariable String id) {

        studentService.deleteStudent(id);

        return ResponseEntity.noContent().build();
    }
}