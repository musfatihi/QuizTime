package ma.musfatihii.QuizTime.DTO.instructor;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class CreateInstructorRequest {
    @NotBlank(message = "Prénom ne doit pas etre vide")
    private String firstName;
    @NotBlank(message = "Nom ne doit pas etre vide")
    private String lastName;
    @NotNull
    private LocalDate birthDate;
    @NotBlank(message = "Adresse ne doit pas etre vide")
    private String address;
    @NotBlank(message = "Spécialité ne doit pas etre vide")
    private String specialty;
}
