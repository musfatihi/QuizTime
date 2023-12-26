package ma.musfatihii.QuizTime.dto.answers;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ma.musfatihii.QuizTime.model.AnswerCompositeKey;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class AnswersReq {
    @NotNull
    private List<AnswerCompositeKey> answers;
}
