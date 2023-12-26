package ma.musfatihii.QuizTime.dto.instructor;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class InstructorReq {

    @NotBlank(message = "Prénom ne doit pas etre vide")
    private String firstName;

    @NotBlank(message = "Nom ne doit pas etre vide")
    private String lastName;

    @DateTimeFormat
    private LocalDate birthDate;

    @NotBlank(message = "Adresse ne doit pas etre vide")
    private String address;

    @NotBlank(message = "Spécialité ne doit pas etre vide")
    private String specialty;
}
