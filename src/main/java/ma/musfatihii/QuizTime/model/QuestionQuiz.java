package ma.musfatihii.QuizTime.model;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class QuestionQuiz {
    @EmbeddedId
    private QuestionQuizCompositeKey questionQuizCompositeKey;
    private int temporized;
}
