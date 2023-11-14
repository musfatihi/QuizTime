package ma.musfatihii.QuizTime.controller;

import jakarta.validation.Valid;
import ma.musfatihii.QuizTime.DTO.question.CreateQuestionRequest;
import ma.musfatihii.QuizTime.exception.SubjectNotFoundException;
import ma.musfatihii.QuizTime.model.Question;
import ma.musfatihii.QuizTime.service.QuestionService;
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
    public QuestionController(QuestionService questionService) {this.questionService = questionService;}

    @GetMapping
    public List<Question> getAllQuestions() {
        return questionService.getAllQuestions();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Question> getQuestion(@PathVariable Long id) {
        Optional<Question> foundQuestion = questionService.getQuestion(id);
        if (foundQuestion.isPresent()) {
            return ResponseEntity.ok(foundQuestion.get());
        } else {
            throw new SubjectNotFoundException(id);
        }
    }

    @PostMapping
    public ResponseEntity<Question> addNewQuestion(@RequestBody @Valid CreateQuestionRequest createQuestionRequest){
        Question question = new Question(createQuestionRequest.getNbrResponses(),createQuestionRequest.getNbrCorrectResponses(),createQuestionRequest.getContent(),createQuestionRequest.getMaxScore(),createQuestionRequest.getType(),createQuestionRequest.getSubject(),createQuestionRequest.getLevel());
        questionService.addNewQuestion(question);
        return ResponseEntity.status(HttpStatus.CREATED).body(question);
    }

    @ExceptionHandler(SubjectNotFoundException.class)
    public ResponseEntity<String> handleSubjectNotFoundException(SubjectNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

}
