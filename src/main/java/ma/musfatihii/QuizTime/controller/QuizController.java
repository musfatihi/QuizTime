package ma.musfatihii.QuizTime.controller;

import jakarta.validation.Valid;
import ma.musfatihii.QuizTime.dto.quiz.QuizReq;
import ma.musfatihii.QuizTime.dto.quiz.QuizResp;
import ma.musfatihii.QuizTime.service.Implementation.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/quizzes")
@CrossOrigin
public class QuizController {
    private final QuizService quizService;

    @Autowired
    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    @PostMapping
    public ResponseEntity<QuizResp> addQuiz(@RequestBody @Valid QuizReq quizReq)
    {
        return ResponseEntity.status(HttpStatus.CREATED).body(quizService.save(quizReq).get());
    }

    @GetMapping
    public ResponseEntity<List<QuizResp>> getAllQuizzes()
    {
        return ResponseEntity.ok().body(quizService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<QuizResp> getQuiz(@PathVariable Long id)
    {
        return ResponseEntity.status(HttpStatus.FOUND).body(quizService.findById(id).get());
    }

}
