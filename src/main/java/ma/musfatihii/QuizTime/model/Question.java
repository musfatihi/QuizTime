package ma.musfatihii.QuizTime.model;

import jakarta.persistence.*;
import lombok.*;
import ma.musfatihii.QuizTime.enums.QuestionType;

import java.util.List;


@Setter
@Getter
@NoArgsConstructor
@Entity
@Table
public final class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int nbrResponses;

    private int nbrCorrectResponses;

    private String content;

    private double maxScore;

    private QuestionType type;

    @OneToMany(mappedBy = "question",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<Media> mediaList;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subject_id")
    private Subject subject;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "level_id")
    private Level level;

    @OneToMany(mappedBy = "questionQuizCompositeKey.question",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<QuestionQuiz> quizzes;

    @OneToMany(mappedBy = "questionResponseCompositeKey.question",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<QuestionResponse> responses;
}
