package ma.musfatihii.QuizTime.model;


import jakarta.persistence.*;
import lombok.*;
import ma.musfatihii.QuizTime.DTO.level.CreateLevelRequest;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
@Entity
public final class Level {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String description;
    @NonNull
    private double minScore;
    @NonNull
    private double maxScore;

}
