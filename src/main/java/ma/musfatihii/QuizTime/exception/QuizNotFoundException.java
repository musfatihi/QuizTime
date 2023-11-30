package ma.musfatihii.QuizTime.exception;

public class QuizNotFoundException extends RuntimeException{
    public QuizNotFoundException(Long quizId) {
        super("Quiz ayant l'id "+quizId+" est introuvable.");
    }

}
