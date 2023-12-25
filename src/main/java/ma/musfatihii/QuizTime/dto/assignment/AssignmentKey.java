package ma.musfatihii.QuizTime.dto.assignment;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ma.musfatihii.QuizTime.dto.quiz.QuizResp;
import ma.musfatihii.QuizTime.dto.student.StudentResp;


@Getter
@Setter
@NoArgsConstructor
public class AssignmentKey {
    private StudentResp student;
    private QuizResp quiz;
    private int attempt;
}
