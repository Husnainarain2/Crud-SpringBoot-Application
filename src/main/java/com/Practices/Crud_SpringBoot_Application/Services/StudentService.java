package com.Practices.Crud_SpringBoot_Application.Services;

import com.Practices.Crud_SpringBoot_Application.Dto.CreateStudentResponseDto;
import com.Practices.Crud_SpringBoot_Application.Dto.CreateStudentResquestDto;
import com.Practices.Crud_SpringBoot_Application.Dto.UpdateStudentRequestDto;
import com.Practices.Crud_SpringBoot_Application.Dto.UpdateStudentResponseDto;
import com.Practices.Crud_SpringBoot_Application.Entity.student;
import com.Practices.Crud_SpringBoot_Application.ExceptionHandler.DuplicatedResouceException;
import com.Practices.Crud_SpringBoot_Application.ExceptionHandler.ResourcesNotFoundException;
import com.Practices.Crud_SpringBoot_Application.Repository.StudentRepository;
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
        if (emailExists(student)){
            throw new DuplicatedResouceException("Student with email " + student.getEmail() + " already exists");
        }
        student savedStudent=studentRepository.save(student);
        return mapToDto(savedStudent);
    }
    public CreateStudentResponseDto getStudent(Long id){
       student studentRsp=
               studentRepository.findByIdAndDeletedIsFalse(id).orElseThrow(()->
                       new ResourcesNotFoundException("Student with id " + id + " not found"));

        return mapToDto(studentRsp);
    }
    public List<CreateStudentResponseDto> getAllStudent(){
        List<student> studentList=
                studentRepository.findByDeletedIsFalse();
        return studentList.
                stream().map(this::mapToDto).
                toList();
    }
    public UpdateStudentResponseDto updateStudent(Long id,
                                                  UpdateStudentRequestDto studentRequest) {
       student existingStudent=
               studentRepository.findByIdAndDeletedIsFalse(id).orElseThrow(()->
                       new ResourcesNotFoundException("Student with id " + id + " not found"));
        existingStudent.setFirstName(studentRequest.getFirstName());
        existingStudent.setLastName(studentRequest.getLastName());
        existingStudent.setEmail(studentRequest.getEmail());
        existingStudent.setCourse(studentRequest.getCourse());
        existingStudent.setDeleted(false);

        student saveStudent=studentRepository.save(existingStudent);

        return mapToUpdateDto(saveStudent);
    }

    public  void deleteStudent(Long id) {
        student studentToBeDeleted =
                studentRepository
                .findByIdAndDeletedIsFalse(id)
                .orElseThrow(() ->
                        new ResourcesNotFoundException("Student with id " + id + " not found"));
        studentRepository.delete(studentToBeDeleted);
    }

    public boolean deleteAllStudent() {

        if (studentRepository.count()==0){
            return false;
        }
        studentRepository.deleteAll();
        return true;
    }

    public void softDeleteStudent(Long id) {
       student studentToBeDeleted = studentRepository
               .findByIdAndDeletedIsFalse(id).orElseThrow(() ->
                       new ResourcesNotFoundException("Student with id " + id + " not found"));

        studentToBeDeleted.setDeleted(true);
       studentRepository.save(studentToBeDeleted);

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
        responseDto.setCreatedAt(student.getCreatedAt());
        responseDto.setUpdatedAt(student.getUpdatedAt());
        responseDto.setMessage("Student created successfully");
        return responseDto;
 }
 private UpdateStudentResponseDto mapToUpdateDto(student student) {
        UpdateStudentResponseDto responseDto=new UpdateStudentResponseDto();
        responseDto.setFirstName(student.getFirstName());
        responseDto.setLastName(student.getLastName());
        responseDto.setEmail(student.getEmail());
        responseDto.setCourse(student.getCourse());
        responseDto.setMassage("Student updated successfully");
        responseDto.setUpdatedAt(LocalDateTime.now());
        return responseDto;
    }

 private boolean emailExists(student student) {
        return studentRepository.existsByEmail(student.getEmail());
    }

}

