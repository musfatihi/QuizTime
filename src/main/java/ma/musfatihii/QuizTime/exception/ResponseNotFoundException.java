package ma.musfatihii.QuizTime.exception;

public class ResponseNotFoundException extends RuntimeException{
    public ResponseNotFoundException(Long responseId) {
        super("Réponse ayant l'id "+responseId+" est introuvable.");
    }

}
