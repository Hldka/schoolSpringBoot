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
    //b)ögrencilerin notlarini degistirebilsin   ----->AYŞE
    @PutMapping("{studentNo}") // http://localhost:8088/students/105
    public ResponseEntity<String> updateGrade(@PathVariable("studentNo") String studentNo, @Valid @RequestBody StudentDTO studentDTO) {
        studentService.updateGrade(studentNo,studentDTO);

        return ResponseEntity.ok("Grade is updated successfuly");}
    @PutMapping("/update")
    public ResponseEntity<StudentDTO> updateStudent(@RequestBody StudentDTO studentDTO){
        studentService.updateStudent(studentDTO);
        return ResponseEntity.ok(studentDTO);
    }
   // c)ogrenci silebilsin, ---->     kamuran
    @DeleteMapping( "/{studentNo}")
    public  ResponseEntity<String> deletStudent(@PathVariable("studentno") String studentNo){
    studentService.deleteStudent(studentNo);
    return ResponseEntity.ok("deleting is succesfully");
    }
    //e)Istedigi ozellik ile siralama yapabilsin(Isme gore siralama) ---->yavuz selim

    @GetMapping("/sort")
    public  ResponseEntity<List<Student>> mylistbysort(){
        List<Student> students=studentService.getAllBySort();
        return ResponseEntity.ok(students);
    }

    // f)istedigi ozelliklerdeki ogrencileri filtreleyebilsin(Notu 70 den fazla olanlar) ---->????
    @GetMapping("/not")
    public ResponseEntity<List<Student>> getAllByNot(@RequestParam("grade") String grade){
        List<Student> students=studentService.getAllByNot(grade);
        return ResponseEntity.ok(students);
    }

}



