package ma.musfatihii.QuizTime.dto.level;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class UpdateLevelRequest {
    @NotNull
    @Min(1)
    private Long id;

    @NotBlank(message = "La description de niveau ne doit pas etre vide")
    private String description;

    @Min(0)
    @Max(100)
    private double minScore=-1;

    @Min(0)
    @Max(100)
    private double maxScore=-1;
}
