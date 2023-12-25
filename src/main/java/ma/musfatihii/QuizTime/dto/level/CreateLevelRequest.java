package ma.musfatihii.QuizTime.dto.level;


import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateLevelRequest {
    @NotBlank(message = "La description de niveau ne doit pas etre vide")
    private String description;
    @Min(0)
    @Max(100)
    private double minScore=-1;
    @Min(0)
    @Max(100)
    private double maxScore=-1;
}
