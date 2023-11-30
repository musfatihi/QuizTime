package ma.musfatihii.QuizTime.controller;

import jakarta.validation.Valid;
import ma.musfatihii.QuizTime.DTO.answers.CreateAnswerRequest;
import ma.musfatihii.QuizTime.model.Answer;
import ma.musfatihii.QuizTime.service.Implementation.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<Answer> addNewAnswer(@RequestBody @Valid CreateAnswerRequest createAnswerRequest)
    {
        Answer answer = new Answer(createAnswerRequest.getAnswer(),0);
        answerService.save(answer);
        return ResponseEntity.status(HttpStatus.CREATED).body(answer);
    }


}
