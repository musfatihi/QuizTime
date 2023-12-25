package ma.musfatihii.QuizTime.dto.assignment;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import ma.musfatihii.QuizTime.model.AssignmentCompositeKey;

import java.time.LocalDate;

@Getter
@Setter
public class CreateAssignmentRequest {
    @NotNull
    private AssignmentCompositeKey assignmentCompositeKey;
    @NotNull
    private LocalDate startDate;
    @NotNull
    private LocalDate endDate;
}
