package ma.musfatihii.QuizTime.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public final class QuestionResponse {
    @EmbeddedId
    private QuestionResponseCompositeKey questionResponseCompositeKey;
    private double score;
}
