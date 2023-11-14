package ma.musfatihii.QuizTime.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table
public final class Assignment {
    @EmbeddedId
    private AssignmentCompositeKey assignmentCompositeKey;
    private LocalDate startDate;
    private LocalDate endDate;
    private double obtainedScore=-1;
    private boolean passed=false;
}
