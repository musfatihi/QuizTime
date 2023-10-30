package ma.musfatihii.QuizTime.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
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
    @Id
    @ManyToOne
    private Student student;
    @ManyToOne
    private Quiz quiz;
    private int attempt;
    @NonNull
    private LocalDate startDate;
    @NonNull
    private LocalDate endDate;
    private double obtainedScore;
    private boolean passed;
}
