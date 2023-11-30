package ma.musfatihii.QuizTime.DTO.questionsQuiz;

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
