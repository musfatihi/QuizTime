package ma.musfatihii.QuizTime.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ChatMessage {
    private String content;
    private String sender;
    private MessageType messageType;


    private enum MessageType{
        CHAT,LEAVE,JOIN
    }
}
