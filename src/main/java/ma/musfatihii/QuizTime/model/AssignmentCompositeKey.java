package ma.musfatihii.QuizTime.model;

import jakarta.persistence.Embeddable;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Embeddable
public class AssignmentCompositeKey implements Serializable {
    @NotNull
    @ManyToOne
    private Student student;

    @NotNull
    @ManyToOne
    private Quiz quiz;

    @NotNull
    @Min(1)
    private int attempt;
}
