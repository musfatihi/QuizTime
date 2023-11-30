package ma.musfatihii.QuizTime.DTO.media;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ma.musfatihii.QuizTime.model.MediaType;

@Getter
@Setter
@NoArgsConstructor
public class MediaRequest {
    @NotBlank(message = "L'url ne doit pas etre vide")
    private String url;
    @NotNull
    private MediaType mediaType;
}
