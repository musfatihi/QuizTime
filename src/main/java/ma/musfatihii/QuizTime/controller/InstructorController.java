package ma.musfatihii.QuizTime.controller;

import jakarta.validation.Valid;
import ma.musfatihii.QuizTime.DTO.instructor.CreateInstructorRequest;
import ma.musfatihii.QuizTime.model.Instructor;
import ma.musfatihii.QuizTime.model.Level;
import ma.musfatihii.QuizTime.service.InstructorService;
import ma.musfatihii.QuizTime.service.LevelService;
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
    public List<Instructor> getAllInstructors() {
        return instructorService.getAllInstructors();

    }

    @PostMapping
    public ResponseEntity<Instructor> addNewInstructor(@RequestBody @Valid CreateInstructorRequest createInstructorRequest) throws Exception {
        Instructor instructor = new Instructor();
        instructor.setAddress(createInstructorRequest.getAddress());
        instructor.setFirstName(createInstructorRequest.getFirstName());
        instructor.setLastName(createInstructorRequest.getLastName());
        instructor.setBirthDate(createInstructorRequest.getBirthDate());
        instructor.setSpecialty(createInstructorRequest.getSpecialty());
        if(instructorService.addNewInstructor(instructor).isPresent())
        {
            return ResponseEntity.status(HttpStatus.CREATED).body(instructor);
        }else{
            return null;
        }
    }
}
