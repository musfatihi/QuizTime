package ma.musfatihii.QuizTime.exception;


public class LevelNotFoundException extends RuntimeException {
    public LevelNotFoundException(Long levelId) {
        super("Niveau ayant l'id "+levelId+" est introuvable.");
    }
}
