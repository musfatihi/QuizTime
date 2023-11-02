package ma.musfatihii.QuizTime.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table
public final class Response {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String content;

    /*
    @OneToMany(mappedBy = "response")
    private Set<Question> questions = new HashSet<>();

     */

}
