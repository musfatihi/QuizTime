package ma.musfatihii.QuizTime.controller;

import jakarta.validation.Valid;
import ma.musfatihii.QuizTime.DTO.answers.CreateAnswerRequest;
import ma.musfatihii.QuizTime.model.Answer;
import ma.musfatihii.QuizTime.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/answers")
public class AnswerController {
    private AnswerService answerService;

    @Autowired
    public AnswerController(AnswerService answerService)
    {
        this.answerService = answerService;
    }

    @PostMapping
    public ResponseEntity<List<Answer>> addNewAnswer(@RequestBody @Valid CreateAnswerRequest createAnswerRequest)
    {
        List<Answer> answers = answerService.addNewAnswer(createAnswerRequest.getAnswers());

        return ResponseEntity.status(HttpStatus.CREATED).body(answers);
    }


}
