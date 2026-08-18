package com.example.student_management.service;

import com.example.student_management.model.Student;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    private List<Student> students = new ArrayList<>();

    public StudentServiceImpl() {

        students.add(
                new Student(
                        1,
                        "Anand",
                        "anand@example.com",
                        21,
                        "IT"
                )
        );

        students.add(
                new Student(
                        2,
                        "Rahul",
                        "rahul@example.com",
                        22,
                        "CSE"
                )
        );

        students.add(
                new Student(
                        3,
                        "Priya",
                        "priya@example.com",
                        20,
                        "ECE"
                )
        );

        students.add(
                new Student(
                        4,
                        "Aman",
                        "aman@example.com",
                        21,
                        "IT"
                )
        );

        students.add(
                new Student(
                        5,
                        "Neha",
                        "neha@example.com",
                        22,
                        "CSE"
                )
        );
    }

    @Override
    public List<Student> getAllStudents() {
        return students;
    }

    @Override
    public Student getStudentById(int id) {

        for (Student student : students) {

            if (student.getId() == id) {
                return student;
            }
        }

        return null;
    }

    @Override
    public List<Student> getStudentsByName(String name) {

        List<Student> result = new ArrayList<>();

        for (Student student : students) {

            if (student.getName().equalsIgnoreCase(name)) {
                result.add(student);
            }
        }

        return result;
    }

    @Override
    public Student createStudent(Student student) {

        students.add(student);

        return student;
    }

    @Override
    public Student updateStudent(
            int id,
            Student updatedStudent) {

        for (Student student : students) {

            if (student.getId() == id) {

                student.setName(updatedStudent.getName());
                student.setEmail(updatedStudent.getEmail());
                student.setAge(updatedStudent.getAge());
                student.setCourse(updatedStudent.getCourse());

                return student;
            }
        }

        return null;
    }

    @Override
    public boolean deleteStudent(int id) {

        Iterator<Student> iterator = students.iterator();

        while (iterator.hasNext()) {

            Student student = iterator.next();

            if (student.getId() == id) {

                iterator.remove();

                return true;
            }
        }

        return false;
    }
}