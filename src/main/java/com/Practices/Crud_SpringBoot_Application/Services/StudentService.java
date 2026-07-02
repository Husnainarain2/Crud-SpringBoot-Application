package com.Practices.Crud_SpringBoot_Application.Services;

import com.Practices.Crud_SpringBoot_Application.Entity.student;
import com.Practices.Crud_SpringBoot_Application.Repository.StudentRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

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
}
