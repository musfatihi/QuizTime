package ma.musfatihii.QuizTime.DTO.quiz;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import ma.musfatihii.QuizTime.model.Instructor;
@Getter
public class CreateQuizRequest {
    @NotNull
    @Min(0)
    private double successScore;
    @NotNull
    private boolean showResponses;
    @NotNull
    private boolean showResults;
    @NotNull
    @Min(1)
    private int maxAttempts;
    @NotNull
    private String notes;
    @NotNull
    private Instructor instructor;
}
