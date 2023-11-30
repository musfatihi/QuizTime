package ma.musfatihii.QuizTime.DTO.assignment;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ma.musfatihii.QuizTime.DTO.quiz.QuizResp;
import ma.musfatihii.QuizTime.DTO.student.StudentResp;


@Getter
@Setter
@NoArgsConstructor
public class AssignmentKey {
    private StudentResp student;
    private QuizResp quiz;
    private int attempt;
}
