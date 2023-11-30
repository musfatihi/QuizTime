package ma.musfatihii.QuizTime.controller;

import jakarta.validation.Valid;
import ma.musfatihii.QuizTime.DTO.subject.CreateSubjectRequest;
import ma.musfatihii.QuizTime.DTO.subject.SubjectResp;
import ma.musfatihii.QuizTime.DTO.subject.UpdateSubjectRequest;
import ma.musfatihii.QuizTime.exception.SubjectNotFoundException;
import ma.musfatihii.QuizTime.model.Subject;
import ma.musfatihii.QuizTime.service.Implementation.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public List<SubjectResp> getAllSubjects() {
        return subjectService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubjectResp> getSubject(@PathVariable Long id) {
        Optional<SubjectResp> foundSubject = subjectService.findById(id);
        if (foundSubject.isPresent()) {
            return ResponseEntity.ok(foundSubject.get());
        } else {
            throw new SubjectNotFoundException(id);
        }
    }

    @PostMapping
    public ResponseEntity<SubjectResp> addNewSubject(@RequestBody @Valid CreateSubjectRequest createSubjectRequest){
        Subject subject = new Subject(createSubjectRequest.getTitle());
        subject.setParent(createSubjectRequest.getParent());
        return ResponseEntity.status(HttpStatus.CREATED).body(subjectService.save(subject).get());
    }

    @PutMapping
    public ResponseEntity<SubjectResp> updateSubject(@RequestBody @Valid UpdateSubjectRequest updateSubjectRequest){

        Subject subject = new Subject();
        subject.setId(updateSubjectRequest.getId());
        subject.setTitle(updateSubjectRequest.getTitle());
        subject.setParent(updateSubjectRequest.getParent());
        Optional<SubjectResp> optionalSubjectResp = subjectService.update(subject);

        return ResponseEntity.status(HttpStatus.CREATED).body(optionalSubjectResp.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSubject(@PathVariable Long id)
    {
        if(subjectService.delete(id))
        {
            return ResponseEntity.ok("Sujet ayant l'id "+id+" est supprimé avec succès");
        }
        throw new SubjectNotFoundException(id);
    }

}
