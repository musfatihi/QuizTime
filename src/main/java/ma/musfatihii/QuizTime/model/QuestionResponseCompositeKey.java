package ma.musfatihii.QuizTime.model;

import jakarta.persistence.Embeddable;
import jakarta.persistence.ManyToOne;
import lombok.NonNull;

import java.io.Serializable;

@Embeddable
public class QuestionResponseCompositeKey implements Serializable {
    @NonNull
    @ManyToOne
    private Question question;
    @NonNull
    @ManyToOne
    private Response response;
}
