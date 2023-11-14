package ma.musfatihii.QuizTime.repository;

import ma.musfatihii.QuizTime.model.QuestionQuiz;
import ma.musfatihii.QuizTime.model.QuestionQuizCompositeKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface QuestionQuizRepository extends JpaRepository<QuestionQuiz, QuestionQuizCompositeKey> {
}
