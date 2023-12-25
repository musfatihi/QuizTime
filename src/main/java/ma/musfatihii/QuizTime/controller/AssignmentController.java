package ma.musfatihii.QuizTime.controller;

import jakarta.validation.Valid;
import ma.musfatihii.QuizTime.dto.assignment.AssignmentResp;
import ma.musfatihii.QuizTime.dto.assignment.CreateAssignmentRequest;
import ma.musfatihii.QuizTime.model.Assignment;
import ma.musfatihii.QuizTime.service.Implementation.AssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/assignments")
public class AssignmentController {
    private AssignmentService assignmentService;

    @Autowired
    public AssignmentController(AssignmentService assignmentService)
    {
        this.assignmentService = assignmentService;
    }

    @PostMapping
    public ResponseEntity<AssignmentResp> addNewAssignment(@RequestBody @Valid CreateAssignmentRequest createAssignmentRequest)
    {
        Assignment assignment = new Assignment();

        assignment.setAssignmentCompositeKey(createAssignmentRequest.getAssignmentCompositeKey());
        assignment.setStartDate(createAssignmentRequest.getStartDate());
        assignment.setEndDate(createAssignmentRequest.getEndDate());

        return ResponseEntity.status(HttpStatus.CREATED).body(assignmentService.save(assignment).get());

    }

    @GetMapping
    public List<AssignmentResp> getAllAssignments()
    {
        return assignmentService.findAll();
    }
}
