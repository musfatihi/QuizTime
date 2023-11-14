package ma.musfatihii.QuizTime.DTO.response;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class CreateResponseRequest {
    @NotBlank(message = "La réponse ne doit pas etre vide")
    private String content;
}
