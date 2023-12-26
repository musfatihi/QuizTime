package ma.musfatihii.QuizTime.controller;

import jakarta.validation.Valid;
import ma.musfatihii.QuizTime.dto.student.StudentReq;
import ma.musfatihii.QuizTime.dto.student.StudentResp;
import ma.musfatihii.QuizTime.service.Implementation.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/students")
@CrossOrigin
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<StudentResp> addStudent(@RequestBody @Valid StudentReq studentReq)
    {
        return ResponseEntity.status(HttpStatus.CREATED).body(studentService.save(studentReq).get());
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentResp> getStudent(@PathVariable Long id)
    {
        return ResponseEntity.status(HttpStatus.FOUND).body(studentService.findById(id).get());
    }

    @GetMapping
    public ResponseEntity<List<StudentResp>> getAllStudents()
    {
        return ResponseEntity.ok().body(studentService.findAll());
    }
}
