package com.Practices.Crud_SpringBoot_Application.Repository;

import com.Practices.Crud_SpringBoot_Application.Entity.student;
import org.springframework.stereotype.Repository;
import com.Practices.Crud_SpringBoot_Application.Repository.StudentRepository;

@Repository
public class StudentRepository {

    public student saveStudent(student student){
        System.out.println("Inside StudentRepository saveStudent");
        System.out.println("Exiting StudentRepository saveStudent");

//       student student1=new student();
//       student1.setFirstName("Husnain");
//       student1.setLastName("Abid");
//       student1.setAge(20);
//       student1.setGender("Male");

       return  student;
    }
}
