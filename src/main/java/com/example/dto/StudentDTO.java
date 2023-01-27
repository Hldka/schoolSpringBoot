package com.example.dto;

import com.example.domain.Student;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;



@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentDTO {


    @NotNull(message = "first name can not be null")
    @NotBlank(message="fist name can not be white space")


    private String firstName; // A

    private String lastName;

    private  String grade;
private String studentNo;

    public StudentDTO(Student student) {

        this.firstName = student.getName();
        this.lastName = student.getLastName();
        this.grade = student.getGrade();
this.studentNo=student.getStudentNo();
    }
}
