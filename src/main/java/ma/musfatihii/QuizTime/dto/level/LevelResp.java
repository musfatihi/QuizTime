package ma.musfatihii.QuizTime.dto.level;


import lombok.*;
import ma.musfatihii.QuizTime.dto.question.QuestionResp;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class LevelResp {
    private int id;
    private String description;
    private double minScore;
    private double maxScore;
    private List<QuestionResp> questions;
}
