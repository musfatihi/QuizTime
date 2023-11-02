package ma.musfatihii.QuizTime.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table
public abstract class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @NonNull
    protected String firstName;
    @NonNull
    protected String lastName;
    @NonNull
    protected LocalDate birthDate;
    @NonNull
    protected String address;

}
