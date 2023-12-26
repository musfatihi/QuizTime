package ma.musfatihii.QuizTime.dto.questionsQuiz;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class QuestionsQuizResp {
    private List<QuestionQuizResp> questionsQuiz;
}
