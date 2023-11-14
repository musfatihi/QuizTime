package ma.musfatihii.QuizTime.DTO.questionsQuiz;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import ma.musfatihii.QuizTime.model.QuestionQuiz;

import java.util.List;

@Getter
@Setter
public class CreateQuestionsQuizRequest {
    @NotEmpty
    private List<QuestionQuiz> questionsQuiz;
}
