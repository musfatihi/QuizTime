package ma.musfatihii.QuizTime.dto.subject;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SubjectResp {
    private int id;
    private String title;
    //private SubjectResp parent;
    //private List<SubjectRes> children;
}
