package ma.musfatihii.QuizTime.DTO.answers;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import ma.musfatihii.QuizTime.model.AnswerCompositeKey;

import java.util.List;

@Setter
@Getter
public class CreateAnswerRequest {
    @NotNull
    private AnswerCompositeKey answer;
}
