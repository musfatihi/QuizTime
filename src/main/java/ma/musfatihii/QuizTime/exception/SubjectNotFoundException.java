package ma.musfatihii.QuizTime.exception;

public class SubjectNotFoundException extends RuntimeException{
    public SubjectNotFoundException(Long subjectId)
    {
        super("Sujet ayant l'id "+subjectId+" est introuvable.");
    }
}
