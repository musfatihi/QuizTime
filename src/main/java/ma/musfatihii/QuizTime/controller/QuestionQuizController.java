package ma.musfatihii.QuizTime.controller;

import jakarta.validation.Valid;
import ma.musfatihii.QuizTime.dto.questionsQuiz.QuestionsQuizReq;
import ma.musfatihii.QuizTime.dto.questionsQuiz.QuestionsQuizResp;
import ma.musfatihii.QuizTime.service.Implementation.QuestionQuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/questsquiz")
@CrossOrigin
public class QuestionQuizController {
    private final QuestionQuizService questionQuizService;

    @Autowired
    public QuestionQuizController(QuestionQuizService questionQuizService)
    {
        this.questionQuizService = questionQuizService;
    }

    @PostMapping
    public ResponseEntity<QuestionsQuizResp> addQuestionsQuiz(@RequestBody @Valid QuestionsQuizReq questionsQuizReq)
    {
        return ResponseEntity.status(HttpStatus.CREATED).body(questionQuizService.save(questionsQuizReq).get());
    }

}
