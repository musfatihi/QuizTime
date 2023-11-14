package ma.musfatihii.QuizTime.repository;

import ma.musfatihii.QuizTime.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface StudentRepository extends JpaRepository<Student,Long> {
}
