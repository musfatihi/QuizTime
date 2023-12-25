package ma.musfatihii.QuizTime.dto.subject;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import ma.musfatihii.QuizTime.model.Subject;

@Getter
public class CreateSubjectRequest {
    @NotBlank(message = "Le titre de sujet ne doit pas etre vide")
    private String title;

    private Subject parent;
}
