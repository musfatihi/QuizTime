package ma.musfatihii.QuizTime.dto.subject;

import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SubjectId {
    @Positive
    private Long id;
}
