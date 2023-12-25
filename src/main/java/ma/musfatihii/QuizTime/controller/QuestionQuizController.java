package ma.musfatihii.QuizTime.controller;

import jakarta.validation.Valid;
import ma.musfatihii.QuizTime.dto.questionsQuiz.CreateQuestionsQuizRequest;
import ma.musfatihii.QuizTime.model.QuestionQuiz;
import ma.musfatihii.QuizTime.service.Implementation.QuestionQuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/questsquiz")
public class QuestionQuizController {
    private QuestionQuizService questionQuizService;

    @Autowired
    public QuestionQuizController(QuestionQuizService questionQuizService)
    {
        this.questionQuizService = questionQuizService;
    }

    @PostMapping
    public ResponseEntity<List<QuestionQuiz>> addNewQuestionsQuiz(@RequestBody @Valid CreateQuestionsQuizRequest createQuestionsQuizRequest)
    {
        questionQuizService.addNewQuestionsQuiz(createQuestionsQuizRequest.getQuestionsQuiz());

        return ResponseEntity.status(HttpStatus.CREATED).body(createQuestionsQuizRequest.getQuestionsQuiz());
    }

}
