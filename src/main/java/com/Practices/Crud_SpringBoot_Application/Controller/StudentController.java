package com.Practices.Crud_SpringBoot_Application.Controller;

import com.Practices.Crud_SpringBoot_Application.Entity.student;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/student")
public class StudentController {

    @PostMapping("/create")
    public String createStudent(@RequestBody student student){
        System.out.println(student.getFirstName());
        System.out.println(student.getLastName());
        return "student created";
    }

}
