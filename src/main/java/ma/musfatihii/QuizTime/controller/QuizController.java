package ma.musfatihii.QuizTime.controller;

import ma.musfatihii.QuizTime.DTO.quiz.CreateQuizRequest;
import ma.musfatihii.QuizTime.model.Quiz;
import ma.musfatihii.QuizTime.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<Quiz> addNewQuiz(@RequestBody CreateQuizRequest createQuizRequest)
    {
        Quiz quiz = new Quiz(createQuizRequest.getSuccessScore(),createQuizRequest.isShowResponses(),createQuizRequest.isShowResults(),createQuizRequest.getMaxAttempts(),createQuizRequest.getNotes(),createQuizRequest.getInstructor());
        quizService.addNewQuiz(quiz);
        return ResponseEntity.status(HttpStatus.CREATED).body(quiz);
    }
}
