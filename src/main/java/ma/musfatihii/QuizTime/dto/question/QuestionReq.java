package ma.musfatihii.QuizTime.dto.question;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ma.musfatihii.QuizTime.dto.level.LevelId;
import ma.musfatihii.QuizTime.dto.media.MediaRequest;
import ma.musfatihii.QuizTime.dto.subject.SubjectId;
import ma.musfatihii.QuizTime.enums.QuestionType;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class QuestionReq {
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

    @Valid
    private SubjectId subject;

    @Valid
    private LevelId level;

    @Valid
    private List<MediaRequest> mediaList;

}
