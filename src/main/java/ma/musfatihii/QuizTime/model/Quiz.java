package ma.musfatihii.QuizTime.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table
public final class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NonNull
    private double successScore;
    @NonNull
    private boolean showResponses;
    @NonNull
    private boolean showResults;
    @NonNull
    private int maxAttempts;
    @NonNull
    private String notes;
    @ManyToOne
    @NonNull
    private Instructor instructor;

    /*
    @ManyToMany
    @JoinTable(
            name = "question_quiz",
            joinColumns = @JoinColumn(name = "quiz_id"),
            inverseJoinColumns = @JoinColumn(name = "question_id")
    )
    private Set<Question> questions = new HashSet<>();

     */
}
