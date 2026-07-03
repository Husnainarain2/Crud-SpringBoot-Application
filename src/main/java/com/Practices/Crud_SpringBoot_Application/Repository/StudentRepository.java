package com.Practices.Crud_SpringBoot_Application.Repository;

import com.Practices.Crud_SpringBoot_Application.Entity.student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.Practices.Crud_SpringBoot_Application.Repository.StudentRepository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<student, Long> {
    Optional<student> findByIdAndDeletedIsFalse(Long id);
    List<student>  findByDeletedIsFalse();

}

