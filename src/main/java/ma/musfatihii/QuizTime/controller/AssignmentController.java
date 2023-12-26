package ma.musfatihii.QuizTime.controller;

import jakarta.validation.Valid;
import ma.musfatihii.QuizTime.dto.assignment.AssignmentReq;
import ma.musfatihii.QuizTime.dto.assignment.AssignmentResp;
import ma.musfatihii.QuizTime.service.Implementation.AssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/assignments")
@CrossOrigin
public class AssignmentController {
    private final AssignmentService assignmentService;

    @Autowired
    public AssignmentController(AssignmentService assignmentService) {
        this.assignmentService = assignmentService;
    }

    @PostMapping
    public ResponseEntity<AssignmentResp> addAssignment(@RequestBody @Valid AssignmentReq assignmentReq)
    {
        return ResponseEntity.status(HttpStatus.CREATED).body(assignmentService.save(assignmentReq).get());
    }

    @GetMapping
    public ResponseEntity<List<AssignmentResp>> getAllAssignments(){
        return ResponseEntity.ok().body(assignmentService.findAll());
    }
}
