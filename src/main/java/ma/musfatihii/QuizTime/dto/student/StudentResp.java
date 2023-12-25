package ma.musfatihii.QuizTime.dto.student;



import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class StudentResp {
    protected Long id;
    protected String firstName;
    protected String lastName;
    protected LocalDate birthDate;
    protected String address;
    private LocalDate registrationDate;
    //private List<Assignment> assignments;
}
