package ma.musfatihii.QuizTime.controller;

import jakarta.validation.Valid;
import ma.musfatihii.QuizTime.dto.question.QuestionReq;
import ma.musfatihii.QuizTime.dto.question.QuestionResp;
import ma.musfatihii.QuizTime.service.Implementation.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/questions")
@CrossOrigin
public class QuestionController {
    private final QuestionService questionService;

    @Autowired
    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping
    public List<QuestionResp> getAllQuestions() {
        return questionService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<QuestionResp> getQuestion(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.FOUND).body(questionService.findById(id).get());
    }

    @PostMapping
    public ResponseEntity<QuestionResp> addQuestion(@RequestBody @Valid QuestionReq questionReq){
        return ResponseEntity.status(HttpStatus.CREATED).body(questionService.save(questionReq).get());
    }
}
