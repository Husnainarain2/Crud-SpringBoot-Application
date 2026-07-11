package com.Practices.Crud_SpringBoot_Application.Services;

import com.Practices.Crud_SpringBoot_Application.Dto.CreateStudentResponseDto;
import com.Practices.Crud_SpringBoot_Application.Dto.CreateStudentResquestDto;
import com.Practices.Crud_SpringBoot_Application.Entity.student;
import com.Practices.Crud_SpringBoot_Application.Repository.StudentRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public CreateStudentResponseDto createStudent(CreateStudentResquestDto studentRequest) {

        student student=mapToEntity(studentRequest);
        student savedStudent=studentRepository.save(student);
        return mapToDto(savedStudent);
    }
    // findByIdAndDeletedIsFalse = Query -->
    // Select * from student where id=? and deleted=false
    public student getStudent(Long id){
        Optional<student> optionalStudent=
                studentRepository.findByIdAndDeletedIsFalse(id);
        if (optionalStudent.isPresent()){
            return optionalStudent.get();
        }
        return null;
    }
    public List<student> getAllStudent(){
        List<student> studentList=
                studentRepository.findByDeletedIsFalse();
        return studentList;
    }
    public  student updateStudent(Long id, student studentRequest) {
        Optional<student> existingStudent=
                studentRepository.findByIdAndDeletedIsFalse(id);

        if (existingStudent.isEmpty()){
            return null;
        }
        student toUpdate=existingStudent.get();
        toUpdate.setFirstName(studentRequest.getFirstName());
        toUpdate.setLastName(studentRequest.getLastName());
        toUpdate.setAge(studentRequest.getAge());
        toUpdate.setAddress(studentRequest.getAddress());
        toUpdate.setEmail(studentRequest.getEmail());
        toUpdate.setPhone(studentRequest.getPhone());
        toUpdate.setDeleted(false);
        return studentRepository.save(toUpdate);
    }

    public  boolean deleteStudent(Long id) {
        boolean isStudent=
                studentRepository.existsById(id);
        if (!isStudent){
            return false;
        }
        studentRepository.deleteById(id);
        return true;
    }

    public boolean deleteAllStudent() {

        if (studentRepository.count()==0){
            return false;
        }
        studentRepository.deleteAll();
        return true;
    }

    public boolean softDeleteStudent(Long id) {
        Optional<student> existingStudent=
                studentRepository.findByIdAndDeletedIsFalse(id);

        if (existingStudent.isEmpty()){
            return false;
        }
        student toUpdate=existingStudent.get();
        toUpdate.setDeleted(true);
        studentRepository.save(toUpdate);
        return true;
    }

 private student mapToEntity(CreateStudentResquestDto studentRequest) {
 student student=new student();
         student.setFirstName(studentRequest.getFirstName());
         student.setLastName(studentRequest.getLastName());
         student.setAge(studentRequest.getAge());
         student.setDeleted(false);
         student.setEmail(studentRequest.getEmail());
     student.setCreatedAt(LocalDateTime.now());
     student.setUpdatedAt(LocalDateTime.now());
     student.setCourse("Spring Boot");
     student.setGender("Male");
     return student;
 }
 private CreateStudentResponseDto mapToDto(student student) {
        CreateStudentResponseDto responseDto=new CreateStudentResponseDto();
        responseDto.setFirstName(student.getFirstName());
        responseDto.setLastName(student.getLastName());
        responseDto.setAge(student.getAge());
        responseDto.setAddress(student.getAddress());
        responseDto.setEmail(student.getEmail());
        responseDto.setPhone(student.getPhone());
        responseDto.setId(student.getId());
        responseDto.setMessage("Student created successfully");
        return responseDto;
 }


}

