package ma.musfatihii.QuizTime.model;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.*;

import java.util.List;

@RequiredArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
public final class Instructor extends Person{
    @NonNull
    private String specialty;

    @OneToMany
    private List<Quiz> quizzes;
}
