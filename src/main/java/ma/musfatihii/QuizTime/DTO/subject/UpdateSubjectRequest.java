package ma.musfatihii.QuizTime.DTO.subject;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import ma.musfatihii.QuizTime.model.Subject;

@Getter
public class UpdateSubjectRequest {
    @NotNull
    @Min(1)
    private Long id;

    @NotBlank(message = "Le titre de sujet ne doit pas etre vide")
    private String title;

    private Subject parent;
}
