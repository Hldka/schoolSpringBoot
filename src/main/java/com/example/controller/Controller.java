package com.example.controller;

import com.example.domain.Student;
import com.example.dto.StudentDTO;
import com.example.exception.ConflictException;
import com.example.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/students")
public class Controller {
    @Autowired
    private StudentService studentService;
    @PostMapping
    public ResponseEntity<String> saveStudent(@Valid @RequestBody Student student) throws ConflictException {
        studentService.saveStudent(student);
        return ResponseEntity.ok("creating successfull");
    }

    //1. Her ogrenci kendi numarasini girerek kendi notunu görüntüleyebilsin.(HALİDE )
      @GetMapping("{studentNo}")// http://localhost:8080/students/querystudentno
    public ResponseEntity<Student> getStudentByStudentNo(@PathVariable("studentNo") String studentNo){
      Student student=  studentService.findStudentNo(studentNo);
      return ResponseEntity.ok(student);
  }
@GetMapping("/List")
    public ResponseEntity<List<Student>> getAllList(){
        List<Student> list=studentService.getAllList();
        return ResponseEntity.ok(list);

}
//2. Her ögretmen, (User1)
//   a)ögrencilerin sadece listesini görebilsin.Isim ve not(Tüm bilgilerini görmesin) ----> BETÜL
@GetMapping
public ResponseEntity<List<StudentDTO>> getAllStudent(){
    List<StudentDTO> students = studentService.getAllStudent();
    return ResponseEntity.ok(students);
}
}
