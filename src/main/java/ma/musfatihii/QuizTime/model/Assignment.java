package ma.musfatihii.QuizTime.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table
public final class Assignment {
    @EmbeddedId
    private AssignmentCompositeKey assignmentCompositeKey;
    @NonNull
    private LocalDate startDate;
    @NonNull
    private LocalDate endDate;
    private double obtainedScore;
    private boolean passed;
}
