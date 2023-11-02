package ma.musfatihii.QuizTime.model;

import jakarta.persistence.Embeddable;
import jakarta.persistence.ManyToOne;
import lombok.NonNull;

import java.io.Serializable;

@Embeddable
public class AssignmentCompositeKey implements Serializable {
    @NonNull
    @ManyToOne
    private Student student;
    @NonNull
    @ManyToOne
    private Quiz quiz;
    @NonNull
    private int attempt;
}
