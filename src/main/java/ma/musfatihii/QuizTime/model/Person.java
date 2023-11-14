package ma.musfatihii.QuizTime.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Setter
@Getter
@MappedSuperclass
public abstract class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    protected String firstName;

    protected String lastName;

    protected LocalDate birthDate;

    protected String address;

}
