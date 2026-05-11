package com.example.webprog.controller;

import com.example.webprog.model.Student;
import com.example.webprog.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("/api/v2/students")
public class StudentController {

    private final StudentService service;

    public StudentController(StudentService service) {
        this.service = service;
    }

    @GetMapping
    public String showStudents(Model model,
                               Principal principal) {

        model.addAttribute("students", service.getAll());
        model.addAttribute("username", principal.getName());

        return "students";
    }

    @GetMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public String addPage(Model model) {

        model.addAttribute("student", new Student());

        return "add-student";
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public String addStudent(
            @Valid @ModelAttribute Student student,
            BindingResult result
    ) {

        if (result.hasErrors()) {
            return "add-student";
        }

        service.addStudent(student);

        return "redirect:/api/v2/students";
    }

    @GetMapping("/edit/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String editPage(@PathVariable int id,
                           Model model) {

        model.addAttribute("student",
                service.getById(id));

        return "edit-student";
    }

    @PostMapping("/edit")
    @PreAuthorize("hasRole('ADMIN')")
    public String editStudent(@ModelAttribute Student student) {

        service.update(student);

        return "redirect:/api/v2/students";
    }

    @GetMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String delete(@PathVariable int id) {

        service.delete(id);

        return "redirect:/api/v2/students";
    }

    @GetMapping("/search")
    @PreAuthorize("hasAnyRole('MODERATOR','ADMIN')")
    public String search(@RequestParam String name,
                         Model model) {

        model.addAttribute("students",
                service.searchByName(name));

        return "students";
    }
}
