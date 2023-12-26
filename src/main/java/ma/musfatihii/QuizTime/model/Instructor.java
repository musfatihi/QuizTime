package ma.musfatihii.QuizTime.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@Setter
@Getter
@Entity
public final class Instructor extends Person{

    private String specialty;

    @OneToMany(mappedBy = "instructor",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<Quiz> quizzes;
    
}
