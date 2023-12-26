package ma.musfatihii.QuizTime.controller;

import jakarta.validation.Valid;
import ma.musfatihii.QuizTime.dto.instructor.InstructorReq;
import ma.musfatihii.QuizTime.dto.instructor.InstructorResp;
import ma.musfatihii.QuizTime.service.Implementation.InstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/instructors")
@CrossOrigin
public class InstructorController {
    private final InstructorService instructorService;

    @Autowired
    public InstructorController(InstructorService instructorService) {
        this.instructorService = instructorService;
    }

    @GetMapping
    public ResponseEntity<List<InstructorResp>> getAllInstructors() {
        return ResponseEntity.ok().body(instructorService.findAll());
    }

    @PostMapping
    public ResponseEntity<InstructorResp> addInstructor(@RequestBody @Valid InstructorReq instructorReq){
        return ResponseEntity.status(HttpStatus.CREATED).body(instructorService.save(instructorReq).get());
    }
}
