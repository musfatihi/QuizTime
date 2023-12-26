package ma.musfatihii.QuizTime.dto.level;

import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LevelId {
    @Positive
    private Long id;
}
