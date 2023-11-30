package ma.musfatihii.QuizTime.controller;

import jakarta.validation.Valid;
import ma.musfatihii.QuizTime.DTO.questionResponses.CreateQuestionResponsesRequest;
import ma.musfatihii.QuizTime.model.QuestionResponse;
import ma.musfatihii.QuizTime.service.Implementation.QuestionResponsesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/questresps")

public class QuestionResponsesController {
    private QuestionResponsesService questionResponsesService;

    @Autowired
    QuestionResponsesController(QuestionResponsesService questionResponsesService)
    {
        this.questionResponsesService = questionResponsesService;
    }

    @PostMapping
    public ResponseEntity<List<QuestionResponse>> addNewQuestionResponses(@RequestBody @Valid CreateQuestionResponsesRequest createQuestionResponsesRequest){
        questionResponsesService.addNewQuestionResponses(createQuestionResponsesRequest.getQuestionResponses());
        return ResponseEntity.status(HttpStatus.CREATED).body(createQuestionResponsesRequest.getQuestionResponses());
    }

}
