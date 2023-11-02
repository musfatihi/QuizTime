package ma.musfatihii.QuizTime.repository;

import ma.musfatihii.QuizTime.model.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface InstructorRepository extends JpaRepository<Instructor,Long> {
}
