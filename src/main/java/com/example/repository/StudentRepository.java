package com.example.repository;

import com.example.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long>{


    boolean existsBystudentNo(String studentNo);


   Optional <Student> findByStudentNo(String studentNo);
@Query("SELECT s FROM Student s WHERE s.grade>:pnot" )
    List<Student> findAllByNot(@Param("pnot") String grade);
@Query("SELECT s FROM Student s ORDER BY s.name asc")
    List<Student> findAllByName();
    //findAll(Sort.by(Sort.Direction.ASC, "name"));
}
