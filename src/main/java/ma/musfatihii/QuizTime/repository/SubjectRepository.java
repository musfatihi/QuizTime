package ma.musfatihii.QuizTime.repository;

import ma.musfatihii.QuizTime.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface SubjectRepository extends JpaRepository<Subject,Long> {
}
