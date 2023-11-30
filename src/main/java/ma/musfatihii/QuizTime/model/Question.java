package ma.musfatihii.QuizTime.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Setter
@Getter
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

    @OneToMany(mappedBy = "question", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Media> mediaList;

    @NonNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subject_id")
    private Subject subject;

    @NonNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "level_id")
    private Level level;

    @OneToMany(mappedBy = "questionQuizCompositeKey.question", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<QuestionQuiz> quizzes;

    @OneToMany(mappedBy = "questionResponseCompositeKey.question", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<QuestionResponse> responses;

}
