package ma.musfatihii.QuizTime.dto.media;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ma.musfatihii.QuizTime.enums.MediaType;

@Getter
@Setter
@NoArgsConstructor
public class MediaRequest {
    @NotBlank(message = "L'url ne doit pas etre vide")
    private String url;
    @NotNull
    private MediaType mediaType;
}
