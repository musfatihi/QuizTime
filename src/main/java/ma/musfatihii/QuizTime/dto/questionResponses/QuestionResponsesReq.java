package ma.musfatihii.QuizTime.dto.questionResponses;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ma.musfatihii.QuizTime.model.QuestionResponse;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class QuestionResponsesReq {
    @NotEmpty
    private List<QuestionResponse> questionResponses;
}
