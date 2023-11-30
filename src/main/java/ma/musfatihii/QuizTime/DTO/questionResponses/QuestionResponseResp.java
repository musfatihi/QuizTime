package ma.musfatihii.QuizTime.DTO.questionResponses;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class QuestionResponseResp {
    private QuestionResponseKey questionResponseCompositeKey;
    private double score;
}
