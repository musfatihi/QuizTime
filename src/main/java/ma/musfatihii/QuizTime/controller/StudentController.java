package ma.musfatihii.QuizTime.controller;

import jakarta.validation.Valid;
import ma.musfatihii.QuizTime.DTO.student.CreateStudentRequest;
import ma.musfatihii.QuizTime.model.Student;
import ma.musfatihii.QuizTime.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/students")
public class StudentController {

    private StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService)
    {
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<Student> addNewStudent(@RequestBody @Valid CreateStudentRequest createStudentRequest)
    {
        Student student = new Student();

        student.setBirthDate(createStudentRequest.getBirthDate());
        student.setAddress(createStudentRequest.getAddress());
        student.setFirstName(createStudentRequest.getFirstName());
        student.setLastName(createStudentRequest.getLastName());

        studentService.addNewStudent(student);

        return ResponseEntity.status(HttpStatus.CREATED).body(student);
    }

}
