package com.nicolasgandrade.securityapp.student;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/v1/students")
public class StudentController {

    private static final List<Student> STUDENTS = Arrays.asList(
            new Student(1, "Nicolas Andrade"),
            new Student(2, "Maria Silva"),
            new Student(3, "Ana Oliveira")
    );

    @GetMapping("/{id}")
    public Student getStudent(@PathVariable("id") Integer id) {
        return STUDENTS.stream()
                .filter(student -> id.equals(student.getStudentId()))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Student" + id + "does not exists"));
    }
}
