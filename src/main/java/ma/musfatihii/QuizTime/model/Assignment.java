package ma.musfatihii.QuizTime.model;

import jakarta.persistence.*;
import jdk.dynalink.linker.LinkerServices;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

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

    @OneToMany
    private List<Answer> answers;
}
