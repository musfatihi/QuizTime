package ma.musfatihii.QuizTime.dto.question;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ma.musfatihii.QuizTime.dto.media.MediaResp;
import ma.musfatihii.QuizTime.dto.questionResponses.QuestionResponsesResp;
import ma.musfatihii.QuizTime.enums.QuestionType;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class QuestionResp {
    private int id;
    private int nbrResponses;
    private int nbrCorrectResponses;
    private String content;
    private double maxScore;
    private QuestionType type;
    private List<MediaResp> mediaList;
    private List<QuestionResponsesResp> responses;
}
