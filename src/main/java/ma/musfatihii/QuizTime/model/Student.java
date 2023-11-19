package ma.musfatihii.QuizTime.model;

import jakarta.persistence.Entity;
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

    @OneToMany
    private List<Assignment> assignments;
}
