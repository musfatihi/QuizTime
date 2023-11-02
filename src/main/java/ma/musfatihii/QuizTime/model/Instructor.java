package ma.musfatihii.QuizTime.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table
public final class Instructor extends Person{
    @NonNull
    private String specialty;

}
