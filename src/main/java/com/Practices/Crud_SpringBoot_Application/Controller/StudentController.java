package com.Practices.Crud_SpringBoot_Application.Controller;

import com.Practices.Crud_SpringBoot_Application.Entity.student;
import com.Practices.Crud_SpringBoot_Application.Services.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/student")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/create")
    public ResponseEntity<student> createStudent(@RequestBody student student){

       student createdStudent= studentService.createStudent(student);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdStudent);
    }

}
