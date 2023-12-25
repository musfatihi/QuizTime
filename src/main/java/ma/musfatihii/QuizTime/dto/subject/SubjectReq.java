package ma.musfatihii.QuizTime.dto.subject;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SubjectReq {
    private Long id;
    @NotBlank(message = "Le titre de sujet ne doit pas etre vide")
    private String title;
    @Valid
    private SubjectId parent;
}
