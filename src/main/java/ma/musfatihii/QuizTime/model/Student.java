package ma.musfatihii.QuizTime.model;

import jakarta.persistence.Entity;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@Setter
@Getter
@Entity
public final class Student extends Person{
    private LocalDate registrationDate = LocalDate.now();
}
