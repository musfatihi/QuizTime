package ma.musfatihii.QuizTime.model;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class QuestionResponseCompositeKey implements Serializable {
    @NonNull
    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;
    @NonNull
    @ManyToOne
    @JoinColumn(name = "response_id")
    private Response response;
}
