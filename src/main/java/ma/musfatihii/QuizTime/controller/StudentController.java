package ma.musfatihii.QuizTime.controller;

import jakarta.validation.Valid;
import ma.musfatihii.QuizTime.dto.student.CreateStudentRequest;
import ma.musfatihii.QuizTime.dto.student.StudentResp;
import ma.musfatihii.QuizTime.model.Student;
import ma.musfatihii.QuizTime.service.Implementation.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<StudentResp> addNewStudent(@RequestBody @Valid CreateStudentRequest createStudentRequest)
    {
        Student student = new Student();

        student.setBirthDate(createStudentRequest.getBirthDate());
        student.setAddress(createStudentRequest.getAddress());
        student.setFirstName(createStudentRequest.getFirstName());
        student.setLastName(createStudentRequest.getLastName());

        return ResponseEntity.status(HttpStatus.CREATED).body(studentService.save(student).get());
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentResp> getStudent(@PathVariable Long id)
    {
        return ResponseEntity.status(HttpStatus.OK).body(studentService.findById(id).get());
    }

    @GetMapping
    public ResponseEntity<List<StudentResp>> getAllStudents()
    {
        return ResponseEntity.status(HttpStatus.OK).body(studentService.findAll());
    }

}
