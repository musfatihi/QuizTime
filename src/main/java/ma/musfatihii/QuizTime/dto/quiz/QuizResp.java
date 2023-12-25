package ma.musfatihii.QuizTime.dto.quiz;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ma.musfatihii.QuizTime.dto.questionsQuiz.QuestionQuizResp;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class QuizResp {
    private Long id;
    private double successScore;
    private boolean showResponses;
    private boolean showResults;
    private int maxAttempts;
    private String notes;
    //private InstructorResp instructor;
    private List<QuestionQuizResp> questions;
}
