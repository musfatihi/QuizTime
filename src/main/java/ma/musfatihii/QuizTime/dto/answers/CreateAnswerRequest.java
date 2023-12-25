package ma.musfatihii.QuizTime.dto.answers;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import ma.musfatihii.QuizTime.model.AnswerCompositeKey;

@Setter
@Getter
public class CreateAnswerRequest {
    @NotNull
    private AnswerCompositeKey answer;
}
