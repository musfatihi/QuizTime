package ma.musfatihii.QuizTime.controller;

import jakarta.validation.Valid;
import ma.musfatihii.QuizTime.dto.answers.AnswersReq;
import ma.musfatihii.QuizTime.service.Implementation.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/answers")
@CrossOrigin
public class AnswerController {
    private final AnswerService answerService;

    @Autowired
    public AnswerController(AnswerService answerService) {
        this.answerService = answerService;
    }

    @PostMapping
    public ResponseEntity<String> addAnswers(@RequestBody @Valid AnswersReq answersReq)
    {
        answerService.save(answersReq);
        return ResponseEntity.status(HttpStatus.CREATED).body("OK");
    }
}
