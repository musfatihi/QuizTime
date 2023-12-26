package ma.musfatihii.QuizTime.dto.media;

import lombok.*;
import ma.musfatihii.QuizTime.enums.MediaType;

@Getter
@Setter
@NoArgsConstructor
public class MediaResp {
    private int id;
    private String url;
    private MediaType mediaType;
}
