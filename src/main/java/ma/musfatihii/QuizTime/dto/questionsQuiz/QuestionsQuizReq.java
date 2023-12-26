package ma.musfatihii.QuizTime.dto.questionsQuiz;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ma.musfatihii.QuizTime.model.QuestionQuiz;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class QuestionsQuizReq {
    @NotEmpty
    private List<QuestionQuiz> questionsQuiz;
}
