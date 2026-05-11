package com.example.webprog.configuration;

import com.example.webprog.model.Student;
import com.example.webprog.service.StudentService;
import com.example.webprog.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.*;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner init(StudentService studentService,
                           UserService userService) {

        return args -> {

            studentService.addStudent(
                    new Student("Taras", 20, "taras@gmail.com"));

            studentService.addStudent(
                    new Student("Oksana", 22, "oksana@gmail.com"));

            studentService.addStudent(
                    new Student("Ivan", 19, "ivan@gmail.com"));

            userService.createUser(
                    "admin",
                    "1234",
                    "admin@gmail.com",
                    "ROLE_ADMIN");

            userService.createUser(
                    "moderator",
                    "1234",
                    "moderator@gmail.com",
                    "ROLE_MODERATOR");

            userService.createUser(
                    "user",
                    "1234",
                    "user@gmail.com",
                    "ROLE_USER");
        };
    }
}
