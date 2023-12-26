package ma.musfatihii.QuizTime.controller;

import jakarta.validation.Valid;
import ma.musfatihii.QuizTime.dto.questionResponses.QuestionResponsesReq;
import ma.musfatihii.QuizTime.model.QuestionResponse;
import ma.musfatihii.QuizTime.service.Implementation.QuestionResponsesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/questresps")
@CrossOrigin
public class QuestionResponsesController {
    private final QuestionResponsesService questionResponsesService;

    @Autowired
    QuestionResponsesController(QuestionResponsesService questionResponsesService)
    {
        this.questionResponsesService = questionResponsesService;
    }

    @PostMapping
    public ResponseEntity<List<QuestionResponse>> addQuestionResponses(@RequestBody @Valid QuestionResponsesReq questionResponsesReq){
        questionResponsesService.save(questionResponsesReq);
        return ResponseEntity.status(HttpStatus.CREATED).body(questionResponsesReq.getQuestionResponses());
    }

}
