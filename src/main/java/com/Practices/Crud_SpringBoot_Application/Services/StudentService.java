package com.Practices.Crud_SpringBoot_Application.Services;

import com.Practices.Crud_SpringBoot_Application.Entity.student;
import com.Practices.Crud_SpringBoot_Application.Repository.StudentRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public student createStudent(student studentRequest) {

       student studentresponse=
               studentRepository.save(studentRequest);

        // Implementation for creating a student
        return studentresponse;
    }
    public student getStudent(Long id){
        Optional<student> optionalStudent= studentRepository.findById(id);
        if (optionalStudent.isPresent()){
            return optionalStudent.get();
        }
        return null;
    }
    public List<student> getAllStudent(){
        List<student> studentList=studentRepository.findAll();
        return studentList;
    }
    public  student updateStudent(Long id, student studentRequest) {
        Optional<student> existingStudent= studentRepository.findById(id);
                studentRepository.findById(id);
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
}
