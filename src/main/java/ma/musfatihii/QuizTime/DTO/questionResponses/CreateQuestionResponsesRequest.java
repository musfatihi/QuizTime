package ma.musfatihii.QuizTime.DTO.questionResponses;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import ma.musfatihii.QuizTime.model.QuestionResponse;

import java.util.List;

@Getter
public class CreateQuestionResponsesRequest {
    @NotEmpty
    private List<QuestionResponse> questionResponses;
}
