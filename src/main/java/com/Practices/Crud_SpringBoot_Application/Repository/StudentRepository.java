package com.Practices.Crud_SpringBoot_Application.Repository;

import com.Practices.Crud_SpringBoot_Application.Entity.student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.Practices.Crud_SpringBoot_Application.Repository.StudentRepository;

@Repository
public interface StudentRepository extends JpaRepository<student, Long> {

    }

