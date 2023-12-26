package ma.musfatihii.QuizTime.model;


import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public final class Level {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    private double minScore;

    private double maxScore;

    @OneToMany(mappedBy = "level",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<Question> questions=new ArrayList<>();

}
