package ma.musfatihii.QuizTime.DTO.assignment;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class AssignmentResp {
    private AssignmentKey assignmentCompositeKey;
    private LocalDate startDate;
    private LocalDate endDate;
    private double obtainedScore=-1;
    private boolean passed=false;
}
