package ma.musfatihii.QuizTime.dto.level;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LevelReq {
    private Long id;
    @NotBlank(message = "La description de niveau ne doit pas etre vide")
    private String description;
    @Positive(message = "Le Score minimal doit etre positif")
    private double minScore;
    @Positive(message = "Le Score minimal doit etre positif")
    private double maxScore;
}
