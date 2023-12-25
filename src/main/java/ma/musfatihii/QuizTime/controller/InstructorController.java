package ma.musfatihii.QuizTime.controller;

import jakarta.validation.Valid;
import ma.musfatihii.QuizTime.dto.instructor.CreateInstructorRequest;
import ma.musfatihii.QuizTime.dto.instructor.InstructorResp;
import ma.musfatihii.QuizTime.model.Instructor;
import ma.musfatihii.QuizTime.service.Implementation.InstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/instructors")
public class InstructorController {
    private final InstructorService instructorService;

    @Autowired
    public InstructorController(InstructorService instructorService)
    {
        this.instructorService = instructorService;
    }

    @GetMapping
    public List<InstructorResp> getAllInstructors() {
        return instructorService.findAll();
    }

    @PostMapping
    public ResponseEntity<InstructorResp> addNewInstructor(@RequestBody @Valid CreateInstructorRequest createInstructorRequest) throws Exception {
        Instructor instructor = new Instructor();
        instructor.setAddress(createInstructorRequest.getAddress());
        instructor.setFirstName(createInstructorRequest.getFirstName());
        instructor.setLastName(createInstructorRequest.getLastName());
        instructor.setBirthDate(createInstructorRequest.getBirthDate());
        instructor.setSpecialty(createInstructorRequest.getSpecialty());

        return ResponseEntity.status(HttpStatus.CREATED).body(instructorService.save(instructor).get());
    }
}
