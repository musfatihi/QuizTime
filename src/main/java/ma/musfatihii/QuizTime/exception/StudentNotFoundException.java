package ma.musfatihii.QuizTime.exception;

public class StudentNotFoundException extends RuntimeException{
    public StudentNotFoundException(Long studentId) {
        super("Etudiant ayant l'id "+studentId+" est introuvable.");
    }

}
