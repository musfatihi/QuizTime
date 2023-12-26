package ma.musfatihii.QuizTime.dto.assignment;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ma.musfatihii.QuizTime.model.AssignmentCompositeKey;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class AssignmentReq {

    @NotNull
    private AssignmentCompositeKey assignmentCompositeKey;

    @DateTimeFormat
    private LocalDate startDate;
    
    @DateTimeFormat
    private LocalDate endDate;
}
