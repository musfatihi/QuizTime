package ma.musfatihii.QuizTime.controller;

import jakarta.validation.Valid;
import ma.musfatihii.QuizTime.DTO.assignment.CreateAssignmentRequest;
import ma.musfatihii.QuizTime.model.Assignment;
import ma.musfatihii.QuizTime.service.AssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<Assignment> addNewAssignment(@RequestBody @Valid CreateAssignmentRequest createAssignmentRequest)
    {
        Assignment assignment = new Assignment();

        assignment.setAssignmentCompositeKey(createAssignmentRequest.getAssignmentCompositeKey());
        assignment.setStartDate(createAssignmentRequest.getStartDate());
        assignment.setEndDate(createAssignmentRequest.getEndDate());

        assignmentService.addNewAssignment(assignment);

        return ResponseEntity.status(HttpStatus.CREATED).body(assignment);

    }
}
