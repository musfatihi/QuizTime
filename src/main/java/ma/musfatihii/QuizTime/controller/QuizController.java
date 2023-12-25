package ma.musfatihii.QuizTime.controller;

import ma.musfatihii.QuizTime.dto.quiz.CreateQuizRequest;
import ma.musfatihii.QuizTime.dto.quiz.QuizResp;
import ma.musfatihii.QuizTime.model.Quiz;
import ma.musfatihii.QuizTime.service.Implementation.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/quizzes")
public class QuizController {
    private QuizService quizService;

    @Autowired
    public QuizController(QuizService quizService)
    {
        this.quizService = quizService;
    }

    @PostMapping
    public ResponseEntity<QuizResp> addNewQuiz(@RequestBody CreateQuizRequest createQuizRequest)
    {
        Quiz quiz = new Quiz(createQuizRequest.getSuccessScore(),createQuizRequest.isShowResponses(),createQuizRequest.isShowResults(),createQuizRequest.getMaxAttempts(),createQuizRequest.getNotes(),createQuizRequest.getInstructor());
        return ResponseEntity.status(HttpStatus.CREATED).body(quizService.save(quiz).get());
    }

    @GetMapping
    public ResponseEntity<List<QuizResp>> getAllQuizzes()
    {
        return ResponseEntity.status(HttpStatus.OK).body(quizService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<QuizResp> getQuiz(@PathVariable Long id)
    {
        return ResponseEntity.status(HttpStatus.OK).body(quizService.findById(id).get());
    }

}
