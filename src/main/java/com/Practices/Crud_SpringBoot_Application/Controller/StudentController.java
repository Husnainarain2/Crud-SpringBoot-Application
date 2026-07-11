package com.Practices.Crud_SpringBoot_Application.Controller;

import com.Practices.Crud_SpringBoot_Application.Dto.CreateStudentResquestDto;
import com.Practices.Crud_SpringBoot_Application.Entity.student;
import com.Practices.Crud_SpringBoot_Application.Services.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/student")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/create")
    public ResponseEntity<student> createStudent(@RequestBody CreateStudentResquestDto resquestDto){

        CreateStudentResquestDto createdStudent= studentService.createStudent(resquestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdStudent);
    }

    // Read only one student method
    @GetMapping("/get/{id}")
    public ResponseEntity<student> getStudent(@PathVariable Long id){
        student studentreadResp= studentService.getStudent(id);

        if (studentreadResp==null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(studentreadResp);
    }
    // Read all student record method
    @GetMapping("/getAll")
    public ResponseEntity<List<student>> getAllStudents(){
        List<student> studentList=
                studentService.getAllStudent();
        if (studentList==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(studentList);
    }

    // Uodate student record method
    @PutMapping("/update/{id}")
    public ResponseEntity<student> updateStudent(@PathVariable Long id , @RequestBody student student){
        student updateStudent=
                studentService.updateStudent(id
                        ,student);
        if (updateStudent==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updateStudent);
    }
    // Delete method
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable Long id){
        boolean isDelete=
                studentService.deleteStudent(id);
        if (!isDelete){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok("Deleted");
    }
    //Hard Delete all student record method
    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteAllStudents(){
        boolean studentList=
                studentService.deleteAllStudent();
        if (!studentList){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No students found to delete.");
        }
        return ResponseEntity.ok("All students " +
                "deleted Successfully");
    }
  // soft Deleted
    @PatchMapping("/soft-delete/{id}")
    public ResponseEntity<String> softDeleteStudent(@PathVariable Long id) {
        boolean softDeleted = studentService.softDeleteStudent(id);
        if (!softDeleted) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No students found to delete.");
        }
        return ResponseEntity.ok("Student soft deleted successfully.");

    }

}
