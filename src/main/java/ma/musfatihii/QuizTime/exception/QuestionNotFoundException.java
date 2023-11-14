package ma.musfatihii.QuizTime.exception;

public class QuestionNotFoundException extends RuntimeException{
    public QuestionNotFoundException(Long questionId){super("Question ayant l'id "+questionId+" est introuvable");}
}
