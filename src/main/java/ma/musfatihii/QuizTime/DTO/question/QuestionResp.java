package ma.musfatihii.QuizTime.DTO.question;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ma.musfatihii.QuizTime.DTO.level.LevelResp;
import ma.musfatihii.QuizTime.DTO.media.MediaResp;
import ma.musfatihii.QuizTime.DTO.questionResponses.QuestionResponseResp;
import ma.musfatihii.QuizTime.DTO.response.ResponseResp;
import ma.musfatihii.QuizTime.DTO.subject.SubjectResp;
import ma.musfatihii.QuizTime.model.QuestionType;
import ma.musfatihii.QuizTime.model.Response;

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
    private List<QuestionResponseResp> responses;
}
