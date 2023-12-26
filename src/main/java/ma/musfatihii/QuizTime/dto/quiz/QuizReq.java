package ma.musfatihii.QuizTime.dto.quiz;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ma.musfatihii.QuizTime.model.Instructor;

@Getter
@Setter
@NoArgsConstructor
public class QuizReq {
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
    @NotBlank
    private String notes;
    @NotNull
    private Instructor instructor;
}
