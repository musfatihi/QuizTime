package ma.musfatihii.QuizTime.model;

import jakarta.persistence.Embeddable;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Embeddable
public class AnswerCompositeKey implements Serializable {
    @NotNull
    @ManyToOne
    private Assignment assignment;
    @NotNull
    @ManyToOne
    private QuestionResponse questionResponse;
}
