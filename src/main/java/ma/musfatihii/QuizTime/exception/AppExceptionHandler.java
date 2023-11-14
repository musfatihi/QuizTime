package ma.musfatihii.QuizTime.exception;

import ma.musfatihii.QuizTime.model.Question;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class AppExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String,String> handleInvalidRequests(MethodArgumentNotValidException ex)
    {
        Map<String,String> errors = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(fieldError ->
        {
            errors.put(fieldError.getField(),fieldError.getDefaultMessage());
        });

        return errors;

    }

    @ExceptionHandler(LevelNotCreatedException.class)
    public ResponseEntity<String> handleLevelNotCreated(LevelNotCreatedException ex)
    {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
    }
    @ExceptionHandler(LevelInfosNotCorrectException.class)
    public ResponseEntity<String> handleLevelInfosNotCorrect(LevelInfosNotCorrectException ex)
    {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(LevelNotFoundException.class)
    public ResponseEntity<String> handleLevelNotFound(LevelNotFoundException ex)
    {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(SubjectNotFoundException.class)
    public ResponseEntity<String> handleSubjectNotFound(SubjectNotFoundException ex)
    {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(SubjectNotCreatedException.class)
    public ResponseEntity<String> handleSubjectNotCreated(SubjectNotCreatedException ex)
    {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
    }

    @ExceptionHandler(QuestionInfosNotCorrectException.class)
    public ResponseEntity<String> handleQuestionInfosNotCorrect(QuestionInfosNotCorrectException ex)
    {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(QuestionNotCreated.class)
    public ResponseEntity<String> handleQuestionNotCreated(QuestionNotCreated ex)
    {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
    }

    @ExceptionHandler(QuestionNotFoundException.class)
    public ResponseEntity<String> handleQuestionNotFound(QuestionNotFoundException ex)
    {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(ResponseNotFoundException.class)
    public ResponseEntity<String> handleResponseNotFound(ResponseNotFoundException ex)
    {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(ResponseNotCreatedException.class)
    public ResponseEntity<String> handleResponseNotCreated(ResponseNotCreatedException ex)
    {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }


    @ExceptionHandler(QuestionResponsesInfosNotCorrectException.class)
    public ResponseEntity<String> handleQuestionResponsesInfosNotCorrect(QuestionResponsesInfosNotCorrectException ex)
    {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }


    @ExceptionHandler(QuestionsQuizInfosNotCorrectException.class)
    public ResponseEntity<String> handleQuestionsQuizInfosNotCorrect(QuestionsQuizInfosNotCorrectException ex)
    {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(QuestionsQuizNotCreatedException.class)
    public ResponseEntity<String> handleQuestionsQuizNotCreated(QuestionsQuizNotCreatedException ex)
    {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
    }

    @ExceptionHandler(AssignmentInfosNotCorrectException.class)
    public ResponseEntity<String> handleAssignmentInfosNotCorrect(AssignmentInfosNotCorrectException ex)
    {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(AssignmentNotCreatedException.class)
    public ResponseEntity<String> handleAssignmentNotCreated(AssignmentNotCreatedException ex)
    {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

}
