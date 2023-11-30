package ma.musfatihii.QuizTime.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Setter
@Getter
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

    @OneToMany(mappedBy = "questionResponseCompositeKey.response", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<QuestionResponse> questions;

}
