package ma.musfatihii.QuizTime.dto.question;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import ma.musfatihii.QuizTime.dto.media.MediaRequest;
import ma.musfatihii.QuizTime.model.Level;
import ma.musfatihii.QuizTime.model.QuestionType;
import ma.musfatihii.QuizTime.model.Subject;

import java.util.List;

@Getter
public class CreateQuestionRequest {

    @Min(2)
    private int nbrResponses;

    @Min(1)
    private int nbrCorrectResponses;

    @NotBlank
    private String content;

    @Min(0)
    private double maxScore;

    @NotNull
    private QuestionType type;

    @NotNull
    private Subject subject;

    @NotNull
    private Level level;

    private List<MediaRequest> mediaList;
}
