package ma.musfatihii.QuizTime.controller;

import jakarta.validation.Valid;
import ma.musfatihii.QuizTime.DTO.media.MediaRequest;
import ma.musfatihii.QuizTime.DTO.question.CreateQuestionRequest;
import ma.musfatihii.QuizTime.DTO.question.QuestionResp;
import ma.musfatihii.QuizTime.exception.QuestionNotFoundException;
import ma.musfatihii.QuizTime.model.Media;
import ma.musfatihii.QuizTime.model.Question;
import ma.musfatihii.QuizTime.service.Implementation.MediaService;
import ma.musfatihii.QuizTime.service.Implementation.QuestionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/questions")
public class QuestionController {
    private QuestionService questionService;

    @Autowired
    private MediaService mediaService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    public QuestionController(QuestionService questionService) {this.questionService = questionService;}

    @GetMapping
    public List<QuestionResp> getAllQuestions() {
        return questionService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<QuestionResp> getQuestion(@PathVariable Long id) {
        Optional<QuestionResp> foundQuestion = questionService.findById(id);
        if (foundQuestion.isPresent()) {
            return ResponseEntity.ok(foundQuestion.get());
        }
        throw new QuestionNotFoundException(id);
    }

    @PostMapping
    public ResponseEntity<QuestionResp> addNewQuestion(@RequestBody @Valid CreateQuestionRequest createQuestionRequest){
        Question question = new Question(createQuestionRequest.getNbrResponses(),createQuestionRequest.getNbrCorrectResponses(),createQuestionRequest.getContent(),createQuestionRequest.getMaxScore(),createQuestionRequest.getType(),createQuestionRequest.getSubject(),createQuestionRequest.getLevel());
        QuestionResp savedQuestion = questionService.save(question).get();
        for (MediaRequest mediaRequest : createQuestionRequest.getMediaList()) {
            Media media = new Media(mediaRequest.getUrl(),mediaRequest.getMediaType(),modelMapper.map(savedQuestion,Question.class));
            mediaService.save(media);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(savedQuestion);
    }


}
