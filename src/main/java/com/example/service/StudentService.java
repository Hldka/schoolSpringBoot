package com.example.service;

import com.example.domain.Student;
import com.example.dto.StudentDTO;
import com.example.exception.ConflictException;
import com.example.exception.ResourceNotFoundException;
import com.example.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {



    @Autowired
    private StudentRepository studentRepository;
    public void saveStudent(Student student) throws ConflictException {
        if (studentRepository.existsBystudentNo(student.getStudentNo())){
            throw new ConflictException("Student no already exists!");
        }
        studentRepository.save(student);
    }




    public List<Student> getAllList() {
        return studentRepository.findAll();
    }

    public Student findStudentNo(String studentNo) {
       return studentRepository.findByStudentNo(studentNo).orElseThrow(()->new ResourceNotFoundException("Student is not found"));
    }
public List<StudentDTO> getAllStudent(){
        List<Student> list=studentRepository.findAll();
        List<StudentDTO> listDTO=new ArrayList<>();
        for (Student s:list){listDTO.add(new StudentDTO(s));}
        return listDTO;
}
    public Student getStudentByStudentNo(String studentNo){
        Student student= (Student) studentRepository.findByStudentNo(studentNo).
                orElseThrow(()->new ResourceNotFoundException("Student not found by studentNo: "+studentNo));
        return student;
    }

    public void updateGrade(String studentNo, StudentDTO studentDTO) {

        Student student = getStudentByStudentNo(studentNo);
        boolean exist=studentRepository.existsBystudentNo(studentDTO.getStudentNo());
        if (exist&& !student.getStudentNo().equals(studentDTO.getStudentNo())){
            throw new ConflictException("this student is already ");

        }

       student.setGrade(studentDTO.getGrade()); // sadece notunu degistirdik
       studentRepository.save(student); //sadece not degistirdi digerleri ayni  s

    }

    public void updateStudent(StudentDTO studentDTO) {

        boolean exist=studentRepository.existsBystudentNo(studentDTO.getStudentNo());
        Student student=getStudentByStudentNo(studentDTO.getStudentNo());
        if (exist&& !student.getStudentNo().equals(studentDTO.getStudentNo())){
            throw new ConflictException("this student is already in");

        }student.setName(studentDTO.getFirstName());
        student.setLastName(studentDTO.getLastName());
        student.setGrade(studentDTO.getGrade());
    }


    public void deleteStudent(String studentNo) {
       Student student= getStudentByStudentNo(studentNo);
        studentRepository.delete(student);
    }

    public List<Student> getAllByNot(String grade) {
      return   studentRepository.findAllByNot(grade);


    }

    public List<Student> getAllBySort() {
        List<Student> students=studentRepository.findAllByName();
        return students;
    }
}
