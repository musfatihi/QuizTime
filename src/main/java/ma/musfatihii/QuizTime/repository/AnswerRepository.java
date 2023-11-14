package ma.musfatihii.QuizTime.repository;

import ma.musfatihii.QuizTime.model.Answer;
import ma.musfatihii.QuizTime.model.AnswerCompositeKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface AnswerRepository extends JpaRepository<Answer, AnswerCompositeKey> {
}
