package ma.musfatihii.QuizTime.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table
public final class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private int nbrResponses;
    @NonNull
    private int nbrCorrectResponses;
    @NonNull
    private String content;
    @NonNull
    private double maxScore;
    @NonNull
    private QuestionType type;
    @NonNull
    @ManyToOne
    private Subject subject;
    @NonNull
    @ManyToOne
    private Level level;
}
