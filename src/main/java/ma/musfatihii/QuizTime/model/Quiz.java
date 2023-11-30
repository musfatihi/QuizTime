package ma.musfatihii.QuizTime.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.List;
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
    @NonNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "instructor_id")
    private Instructor instructor;

    @OneToMany(mappedBy = "questionQuizCompositeKey.quiz", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<QuestionQuiz> questions;

    @OneToMany
    private List<Assignment> assignments;
}
