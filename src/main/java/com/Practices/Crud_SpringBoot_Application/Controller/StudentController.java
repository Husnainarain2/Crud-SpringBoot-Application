package com.Practices.Crud_SpringBoot_Application.Controller;

import com.Practices.Crud_SpringBoot_Application.Entity.student;
import com.Practices.Crud_SpringBoot_Application.Services.StudentService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/student")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/create")
    public student createStudent(@RequestBody student student){
        System.out.println("inside StudentController createStudent");
       student createdStudent= studentService.createStudent(student);
        System.out.println("Exiting StudentController createStudent");
        return createdStudent;
    }

}
