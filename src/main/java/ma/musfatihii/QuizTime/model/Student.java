package ma.musfatihii.QuizTime.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table
public final class Student extends Person{
    @NonNull
    private LocalDate registrationDate;
}
