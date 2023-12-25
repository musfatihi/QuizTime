package ma.musfatihii.QuizTime.dto.questionsQuiz;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class QuestionQuizResp {
    private QuestionQuizKey questionQuizCompositeKey;
    private int temporized;
}
