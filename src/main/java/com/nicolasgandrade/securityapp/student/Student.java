package com.nicolasgandrade.securityapp.student;

public class Student {

    private final Integer id;
    private final String studentName;

    public Student(Integer id, String studentName) {
        this.id = id;
        this.studentName = studentName;
    }

    public Integer getStudentId() {
        return id;
    }

    public String getStudentName() {
        return studentName;
    }
}
