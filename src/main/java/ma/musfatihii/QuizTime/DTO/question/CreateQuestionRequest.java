package ma.musfatihii.QuizTime.DTO.question;


import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import ma.musfatihii.QuizTime.model.Level;
import ma.musfatihii.QuizTime.model.QuestionType;
import ma.musfatihii.QuizTime.model.Subject;

@Getter
public class CreateQuestionRequest {

    @NotNull
    @Min(2)
    private int nbrResponses;

    @NotNull
    @Min(1)
    private int nbrCorrectResponses;

    @NotBlank
    private String content;

    @NotNull
    @Min(0)
    private double maxScore;

    @NotNull
    private QuestionType type;

    @NotNull
    private Subject subject;

    @NotNull
    private Level level;
}
