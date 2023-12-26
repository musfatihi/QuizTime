package ma.musfatihii.QuizTime.dto.response;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ResponseReq {
    @NotBlank(message = "La réponse ne doit pas etre vide")
    private String content;
}
