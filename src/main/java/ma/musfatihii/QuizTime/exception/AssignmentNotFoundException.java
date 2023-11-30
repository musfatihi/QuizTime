package ma.musfatihii.QuizTime.exception;

public class AssignmentNotFoundException extends RuntimeException{
    public AssignmentNotFoundException() {
        super("Affectation est introuvable.");
    }
}
