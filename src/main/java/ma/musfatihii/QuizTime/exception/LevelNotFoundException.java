package ma.musfatihii.QuizTime.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class LevelNotFoundException extends RuntimeException {
    public LevelNotFoundException(Long levelId) {
        super("Niveau ayant l'id "+levelId+" est introuvable.");
    }
}
