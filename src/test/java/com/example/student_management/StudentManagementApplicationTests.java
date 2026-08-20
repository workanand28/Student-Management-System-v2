package com.example.student_management;

import com.example.student_management.model.Student;
import com.example.student_management.repository.StudentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StudentManagementApplicationTests {

	@Autowired
	private StudentRepository studentRepository;

	@Test
	void contextLoads() {
		assertNotNull(studentRepository);
	}

	@Test
	void testMongoDbOperations() {
		// Create a test student
		Student student = new Student();
		student.setName("Test Student");
		student.setEmail("test.student@example.com");
		student.setAge(21);
		student.setCourse("Computer Science");

		Student savedStudent = studentRepository.save(student);
		assertNotNull(savedStudent.getId());
		assertEquals("Test Student", savedStudent.getName());

		// Find by ID
		Student foundStudent = studentRepository.findById(savedStudent.getId()).orElse(null);
		assertNotNull(foundStudent);
		assertEquals("test.student@example.com", foundStudent.getEmail());

		// Search by Name Ignore Case
		List<Student> searchResults = studentRepository.findByNameIgnoreCase("test student");
		assertFalse(searchResults.isEmpty());

		// Clean up test student
		studentRepository.deleteById(savedStudent.getId());
		assertFalse(studentRepository.existsById(savedStudent.getId()));
	}
}
