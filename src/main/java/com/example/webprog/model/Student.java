package com.example.webprog.model;

import jakarta.validation.constraints.*;

public class Student {

    private static int counter = 1;

    private int id;

    @NotBlank(message = "Ім'я не може бути порожнім")
    private String name;

    @Min(value = 16)
    @Max(value = 100)
    private int age;

    @Email
    @NotBlank
    private String email;

    public Student() {
        this.id = counter++;
    }

    public Student(String name, int age, String email) {
        this.id = counter++;
        this.name = name;
        this.age = age;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    // ОСЬ ЦЬОГО НЕ ВИСТАЧАЄ
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
