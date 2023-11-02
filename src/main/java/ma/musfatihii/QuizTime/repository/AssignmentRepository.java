package ma.musfatihii.QuizTime.repository;

import ma.musfatihii.QuizTime.model.Assignment;
import ma.musfatihii.QuizTime.model.AssignmentCompositeKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssignmentRepository extends JpaRepository<Assignment, AssignmentCompositeKey> {
}
