package ma.musfatihii.QuizTime.controller;

import jakarta.validation.Valid;
import ma.musfatihii.QuizTime.DTO.subject.CreateSubjectRequest;
import ma.musfatihii.QuizTime.DTO.subject.UpdateSubjectRequest;
import ma.musfatihii.QuizTime.exception.SubjectNotFoundException;
import ma.musfatihii.QuizTime.model.Subject;
import ma.musfatihii.QuizTime.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/subjects")
public class SubjectController {
    private SubjectService subjectService;

    @Autowired
    public SubjectController(SubjectService subjectService)
    {
        this.subjectService = subjectService;
    }

    @GetMapping
    public List<Subject> getAllSubjects() {
        return subjectService.getAllSubjects();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Subject> getSubject(@PathVariable Long id) {
        Optional<Subject> foundSubject = subjectService.getSubject(id);
        if (foundSubject.isPresent()) {
            return ResponseEntity.ok(foundSubject.get());
        } else {
            throw new SubjectNotFoundException(id);
        }
    }

    @PostMapping
    public ResponseEntity<Subject> addNewSubject(@RequestBody @Valid CreateSubjectRequest createSubjectRequest){
        Subject subject = new Subject(createSubjectRequest.getTitle());
        subject.setParent(createSubjectRequest.getParent());
        subjectService.addNewSubject(subject);
        return ResponseEntity.status(HttpStatus.CREATED).body(subject);
    }

    @PutMapping
    public ResponseEntity<Subject> updateSubject(@RequestBody @Valid UpdateSubjectRequest updateSubjectRequest){
        Subject subject = new Subject(updateSubjectRequest.getId(),updateSubjectRequest.getTitle(),updateSubjectRequest.getParent());
        Optional<Subject> optionalSubject = subjectService.updateSubject(subject);
        if(optionalSubject.isPresent())
        {
            return ResponseEntity.status(HttpStatus.CREATED).body(optionalSubject.get());
        } else
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSubject(@PathVariable Long id)
    {
        if(subjectService.deleteSubject(id))
        {
            return ResponseEntity.ok("Sujet ayant l'id "+id+" est supprimé avec succès");
        }else{
            throw new SubjectNotFoundException(id);
        }
    }

    @ExceptionHandler(SubjectNotFoundException.class)
    public ResponseEntity<String> handleSubjectNotFoundException(SubjectNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}
