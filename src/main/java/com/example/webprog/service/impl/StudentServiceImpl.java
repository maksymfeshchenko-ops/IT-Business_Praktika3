package com.example.webprog.service.impl;

import com.example.webprog.model.Student;
import com.example.webprog.repository.StudentRepository;
import com.example.webprog.service.StudentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository repository;

    public StudentServiceImpl(StudentRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Student> getAll() {
        return repository.findAll();
    }

    @Override
    public void addStudent(Student student) {
        repository.save(student);
    }

    @Override
    public Student getById(int id) {
        return repository.findById(id);
    }

    @Override
    public void update(Student student) {

        Student existing = repository.findById(student.getId());

        if (existing != null) {
            existing.setName(student.getName());
            existing.setAge(student.getAge());
            existing.setEmail(student.getEmail());
        }
    }

    @Override
    public void delete(int id) {
        repository.delete(id);
    }

    @Override
    public List<Student> searchByName(String name) {
        return repository.findByName(name);
    }
}
