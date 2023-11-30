package ma.musfatihii.QuizTime.model;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
@Embeddable
public class QuestionQuizCompositeKey implements Serializable {
    @NonNull
    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;
    @NonNull
    @ManyToOne
    @JoinColumn(name = "quiz_id")
    private Quiz quiz;
}
