package ma.musfatihii.QuizTime.DTO.instructor;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ma.musfatihii.QuizTime.DTO.quiz.QuizResp;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class InstructorResp {
    private Long id;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private String address;
    private String specialty;
    private List<QuizResp> quizzes;
}
