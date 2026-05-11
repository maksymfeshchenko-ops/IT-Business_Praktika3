package com.example.webprog.repository;

import com.example.webprog.model.Student;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class StudentRepository {

    private final List<Student> students = new ArrayList<>();

    public List<Student> findAll() {
        return students;
    }

    public void save(Student student) {
        students.add(student);
    }

    public Student findById(int id) {
        return students.stream()
                .filter(s -> s.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public void delete(int id) {
        students.removeIf(s -> s.getId() == id);
    }

    public List<Student> findByName(String name) {
        return students.stream()
                .filter(s -> s.getName().toLowerCase().contains(name.toLowerCase()))
                .toList();
    }
}
