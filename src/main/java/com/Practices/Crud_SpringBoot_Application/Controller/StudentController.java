package com.Practices.Crud_SpringBoot_Application.Controller;

import com.Practices.Crud_SpringBoot_Application.Dto.CreateStudentResponseDto;
import com.Practices.Crud_SpringBoot_Application.Dto.CreateStudentResquestDto;
import com.Practices.Crud_SpringBoot_Application.Dto.UpdateStudentRequestDto;
import com.Practices.Crud_SpringBoot_Application.Dto.UpdateStudentResponseDto;
import com.Practices.Crud_SpringBoot_Application.Entity.student;
import com.Practices.Crud_SpringBoot_Application.Services.StudentService;
import jakarta.validation.Valid;
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

    @PostMapping
    public ResponseEntity<CreateStudentResponseDto> createStudent(@Valid @RequestBody CreateStudentResquestDto resquestDto){

        CreateStudentResponseDto createdStudent= studentService.createStudent(resquestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdStudent);
    }

    // Read only one student method
    @GetMapping("/{id}")
    public ResponseEntity<CreateStudentResponseDto> getStudent(@PathVariable Long id){
        CreateStudentResponseDto studentreadResp=
                studentService.getStudent(id);

        return ResponseEntity.ok(studentreadResp);
    }
    // Read all student record method
    @GetMapping
    public ResponseEntity<List<CreateStudentResponseDto>> getAllStudents(){
        List<CreateStudentResponseDto> studentList=
                studentService.getAllStudent();
        if (studentList==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(studentList);
    }

    // Uodate student record method
    @PutMapping("/{id}")
    public ResponseEntity<UpdateStudentResponseDto> updateStudent(@PathVariable Long id , @RequestBody UpdateStudentRequestDto student){
        UpdateStudentResponseDto updateStudent=
                studentService.updateStudent(id,student);
        return ResponseEntity.ok(updateStudent);
    }
    // Delete method
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable Long id){

        studentService.deleteStudent(id);

        return ResponseEntity.ok("Deleted");
    }
    //Hard Delete all student record method
    @DeleteMapping
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
       studentService.softDeleteStudent(id);

        return ResponseEntity.ok("Student soft deleted successfully.");

    }

}
