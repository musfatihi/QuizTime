package ma.musfatihii.QuizTime.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public final class Answer {
    @EmbeddedId
    private AnswerCompositeKey answerCompositeKey;
    private double score=0;

}
