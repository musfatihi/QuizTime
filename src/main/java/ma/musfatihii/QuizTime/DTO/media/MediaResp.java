package ma.musfatihii.QuizTime.DTO.media;

import lombok.*;
import ma.musfatihii.QuizTime.model.MediaType;

@Getter
@Setter
@NoArgsConstructor
public class MediaResp {
    private int id;
    private String url;
    private MediaType mediaType;
}
