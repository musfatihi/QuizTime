package ma.musfatihii.QuizTime.repository;

import ma.musfatihii.QuizTime.model.QuestionResponse;
import ma.musfatihii.QuizTime.model.QuestionResponseCompositeKey;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
public interface QuestionResponseRepository extends CrudRepository<QuestionResponse, QuestionResponseCompositeKey> {
}
