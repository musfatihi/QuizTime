package ma.musfatihii.QuizTime.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@Setter
@Getter
@Entity
public final class Student extends Person{
    private LocalDate registrationDate = LocalDate.now();

    @OneToMany(mappedBy = "assignmentCompositeKey.student", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Assignment> assignments;
}
