package ma.musfatihii.QuizTime.model;

import jakarta.persistence.Embeddable;
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
    private Question question;
    @NonNull
    @ManyToOne
    private Response response;
}
