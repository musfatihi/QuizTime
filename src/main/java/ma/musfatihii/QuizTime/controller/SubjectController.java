package ma.musfatihii.QuizTime.controller;

import jakarta.validation.Valid;
import ma.musfatihii.QuizTime.dto.subject.SubjectReq;
import ma.musfatihii.QuizTime.dto.subject.SubjectResp;
import ma.musfatihii.QuizTime.service.Implementation.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/subjects")
@CrossOrigin
public class SubjectController {
    private final SubjectService subjectService;

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
        return ResponseEntity.status(HttpStatus.FOUND).body(subjectService.findById(id).get());
    }

    @PostMapping
    public ResponseEntity<SubjectResp> addSubject(@RequestBody @Valid SubjectReq subjectReq){
        return ResponseEntity.status(HttpStatus.CREATED).body(subjectService.save(subjectReq).get());
    }

    @PutMapping
    public ResponseEntity<SubjectResp> updateSubject(@RequestBody @Valid SubjectReq subjectReq){
        return ResponseEntity.status(HttpStatus.CREATED).body(subjectService.update(subjectReq).get());
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSubject(@PathVariable Long id)
    {
        subjectService.delete(id);
        return ResponseEntity.ok("Sujet ayant l'id "+id+" est supprimé avec succès");
    }

}
