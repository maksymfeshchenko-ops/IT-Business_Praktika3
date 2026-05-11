package com.example.webprog.service;

import com.example.webprog.model.Student;

import java.util.List;

public interface StudentService {

    List<Student> getAll();

    void addStudent(Student student);

    Student getById(int id);

    void update(Student student);

    void delete(int id);

    List<Student> searchByName(String name);
}
